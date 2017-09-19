package com.har.unmanned.mfront.api.wxUser;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.IRedisService;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.Xml2JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cc on 2017/9/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/wxUserShop", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class WxUserShopResourceImpl implements  WxUserShopResource{
    @Autowired
    private IWxUserShopService wxUserShopService;
    /**
     * 用户首页
     * @param shopCode
     * @return
     */
    @Override
    @GetMapping
    public JSONObject wxUserShop(@RequestParam(value = "shopCode") String shopCode) throws ApiBizException {
        log.info("[wxShop]用户访问首页传入数据:" + shopCode);
        LogHelper.save(LogType.RECEIVE, "[submitOrder]用户访问首页_开始", shopCode);
        RespMessage respMessage = new RespMessage();
        // 返回数据
        JSONObject respJson = wxUserShopService.selectGoodsList(shopCode);
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
    public JSONObject submitOrder(JSONObject params) throws ApiBizException {
        log.info("[submitOrder]用户提交订单传入数据:" + params);
        LogHelper.save(LogType.RECEIVE, "[submitOrder]用户提交订单_开始", params);
        RespMessage respMessage = new RespMessage();
        if (CheckUtil.isNull(params.getString("shopCode"))
                || CheckUtil.isNull(params.getJSONArray("goodsList"))) {
            log.info("提交订单请求参数不全" + params);
            LogHelper.save(LogType.RECEIVE, "提交订单请求参数不全", params);
            throw new ApiBizException(ErrorCode.E00000001.CODE, "提交订单请求参数不全", params);
        }
        // 返回数据
        JSONObject object = wxUserShopService.submitOrder(params);
        log.info("提交订单返回的参数:" + object);
        JSONObject respJson = wxUserShopService.payOrder(object);

        log.info("微信同一下单返回的参数:" + respJson);
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
    public JSONObject buyRecord() {
        RespMessage respMessage = new RespMessage();
        // 返回数据
        log.info("[buyRecord]用户查询购买记录");
        JSONObject respJson = wxUserShopService.buyRecord();
        log.info("[buyRecord]用户查询购买记录响应数据:" + respJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }

    @Override
    @GetMapping("/userInfo")
    public JSONObject userInfo() {
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

    /**
     * 微信支付回调
     * @param params
     * @return
     */
    @GetMapping("/callBack")
    public JSONObject callBack(HttpServletRequest request, @RequestBody String params) throws Exception {
        log.info("[callBack]微信支付回调传入数据:" + params);
        RespMessage respMessage = new RespMessage();
        // 返回数据
        JSONObject reqJson = Xml2JsonUtil.xml2JsonObject(params);
        JSONObject respJson = wxUserShopService.callBack(reqJson);
        log.info("[callBack]微信支付回调响应数据:" + params);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(respJson);
        return respMessage.getRespMessage();
    }
}
