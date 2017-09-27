package com.har.unmanned.mfront.api.wxUser;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.wxUser.validGroup.IndexGroup;
import com.har.unmanned.mfront.api.wxUser.validGroup.OrderGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopOrder;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.WxAuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cc on 2017/9/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wxUserShop", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class WxUserShopResourceImpl implements  WxUserShopResource{
    @Autowired
    private IWxUserShopService wxUserShopService;
    @Autowired
    private WxAuthUtil wxAuthUtil;
    /**
     * 用户首页
     * @param inputParameter
     * @return
     */
    @Override
    @GetMapping
    public JSONObject wxUserShop(@Validated(IndexGroup.class) InputParameter inputParameter) throws Exception {
        log.info("[wxShop]用户访问首页传入数据:" + inputParameter);
        LogHelper.save(LogType.RECEIVE, "[submitOrder]用户访问首页_开始", inputParameter);
        RespMessage respMessage = new RespMessage();
        // 返回数据
        JSONObject respJson = wxUserShopService.selectGoodsList(inputParameter.getShopCode());
        LogHelper.save(LogType.RESPONSE, "[submitOrder]用户访问首页_结束", respJson);
        log.info("[wxShop]用户访问首页响应数据:" + respJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    /**
     * 提交订单
     * @param params
     * @return
     */
    @Override
    @PostMapping("/submitOrder")
    public JSONObject submitOrder(@Validated(OrderGroup.class) @RequestBody InputParameter params) throws Exception {
        log.info("[submitOrder]用户提交订单传入数据:" + params);
        LogHelper.save(LogType.RECEIVE, "[submitOrder]用户提交订单_开始", params);
        RespMessage respMessage = new RespMessage();
        // 返回数据
        ShopOrder shopOrder = wxUserShopService.submitOrder(params);
        log.info("提交订单返回的参数:" + JSONObject.toJSONString(shopOrder));
        JSONObject respJson = wxUserShopService.payOrder(shopOrder);

        log.info("微信下单返回的参数:" + respJson);
        LogHelper.save(LogType.RESPONSE, "[submitOrder]用户提交订单_结束", respJson);
        log.info("[submitOrder]用户访问首页响应数据:" + params);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    /**
     * 用户购买记录
     * @return
     */
    @Override
    @GetMapping("/buyRecord")
    public JSONObject buyRecord(@Validated(IndexGroup.class) InputParameter params) throws Exception {
        RespMessage respMessage = new RespMessage();
        // 返回数据
        log.info("[buyRecord]用户查询购买记录");
        JSONObject respJson = wxUserShopService.buyRecord(params.getShopCode());
        log.info("[buyRecord]用户查询购买记录响应数据:" + respJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    @Override
    @GetMapping("/userInfo")
    public JSONObject userInfo() throws Exception {
        log.info("[userInfo]查询用户信息");
        RespMessage respMessage = new RespMessage();
        // 返回数据
        JSONObject respJson = wxUserShopService.userInfo();
        log.info("[userInfo]查询用户信息响应数据:" + respJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    @Override
    @PostMapping("/getWxSign")
    public JSONObject getWxSign(HttpServletRequest request) throws Exception {
        log.info("[getWxSign]获取微信签名");
        RespMessage respMessage = new RespMessage();
        // 返回数据 currenturl
        String currenturl = CheckUtil.isNull(request.getHeader("currenturl")) ? "" : request.getHeader("currenturl");
        String[] split = currenturl.split("#");
        JSONObject respJson = wxAuthUtil.getWxSignPar(split[0]);
        log.info("[getWxSign]获取微信签名响应数据:" + respJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    /**
     * 微信支付回调
     * @param params
     * @return
     */
    @RequestMapping("/callBack")
    public String callBack(HttpServletRequest request, @RequestBody String params) throws Exception {
        log.info("[callBack]微信支付回调传入数据:" + params);
        StringBuilder sb = new StringBuilder(); // 返回结果, 用于通知微信
        Map resultMap = new HashMap();
        try {
            wxUserShopService.callBack(params);
            log.info("[callBack]微信支付回调响应数据:");
            resultMap.put("return_code", "SUCCESS");
            resultMap.put("return_msg", "支付成功");
            sb.append("<xml>");
            for (Object o : resultMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                sb.append("<").append(entry.getKey()).append(">")
                        .append(entry.getValue())
                        .append("</").append(entry.getKey()).append(">");
            }
            sb.append("</xml>");
        } catch (ApiBizException e) {
            e.printStackTrace();
            LogHelper.saveCommonException(e);
            if ((CheckUtil.isEquals(e.getErrCode(), ErrorCode.E00000018.CODE)) // return_code或result_code不为SUCCESS
                    || (CheckUtil.isEquals(e.getErrCode(), ErrorCode.E00000019.CODE)) // 微信签名验证异常
                    || (CheckUtil.isEquals(e.getErrCode(), ErrorCode.E00000020.CODE))) { // 订单查询结果为支付失败
                resultMap.put("return_code", "FAIL");
                resultMap.put("return_msg", e.getMessage());
            } else if ((CheckUtil.isEquals(e.getErrCode(), ErrorCode.E00000021.CODE)) // 因库存异常导致库存扣减失败
                    || (CheckUtil.isEquals(e.getErrCode(), ErrorCode.E00000022.CODE))) { // 订单和库存更新过程中出现异常
                resultMap.put("return_code", "SUCCESS");
                resultMap.put("return_msg", "支付成功");
            }
            sb.append("<xml>");
            for (Object o : resultMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                sb.append("<").append(entry.getKey()).append(">")
                        .append(entry.getValue())
                        .append("</").append(entry.getKey()).append(">");
            }
            sb.append("</xml>");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("return_code", "SUCCESS");
            resultMap.put("return_msg", "支付成功");
            sb.append("<xml>");
            for (Object o : resultMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                sb.append("<").append(entry.getKey()).append(">")
                        .append(entry.getValue())
                        .append("</").append(entry.getKey()).append(">");
            }
            sb.append("</xml>");
        }
        return sb.toString();
    }
}
