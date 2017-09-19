package com.har.unmanned.mfront.api.demo;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.IRedisService;
import com.har.unmanned.mfront.service.IWxUserShopService;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 2017/9/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wxShop", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class WxUserShopResourceImpl implements  WxUserShopResource{
    @Autowired
    private IWxUserShopService wxUserShopService;
    @Autowired
    private IRedisService redisService;
    /**
     * 用户首页
     * @param params
     * @return
     */
    @Override
    @GetMapping
    public JSONObject wxShop(JSONObject params) {
        log.info("[wxShop]用户访问首页传入数据:" + params);
        RespMessage respMessage = new RespMessage();
        // 返回数据

        try {
            JSONObject respJson = wxUserShopService.selectGoodsList(params);
            respMessage.setRespCode(ErrorCode.E00000000.CODE);
            respMessage.setRespDesc(ErrorCode.E00000000.MSG);
            respMessage.setData(respJson);
        }  catch (Exception e) {
            e.printStackTrace();
            respMessage.setRespCode(ErrorCode.E00000001.CODE);
            respMessage.setRespDesc(ErrorCode.E00000001.MSG);
        }
        return respMessage.getRespMessage();
    }

    /**
     * 提交订单
     * @param params
     * @return
     */
    @Override
    @GetMapping("/submitOrder")
    public JSONObject submitOrder(JSONObject params) {
        return null;
    }

    /**
     * 用户购买记录
     * @param params
     * @return
     */
    @Override
    @GetMapping("/buyRecord")
    public JSONObject buyRecord(JSONObject params) {
        return null;
    }

    @Override
    @GetMapping("/userInfo")
    public JSONObject userInfo(JSONObject params) {
        return null;
    }

    /**
     * 微信支付回调
     * @param params
     * @return
     */
    @GetMapping("/callBack")
    public JSONObject callBack(JSONObject params) {
        return null;
    }
}
