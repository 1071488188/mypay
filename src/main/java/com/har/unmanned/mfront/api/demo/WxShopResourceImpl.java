package com.har.unmanned.mfront.api.demo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiang on 2017/9/18.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wxShop", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class WxShopResourceImpl implements  WxShopResource{

    @Override
    @GetMapping
    public JSONObject wxShop(JSONObject params) {
        return null;
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
}
