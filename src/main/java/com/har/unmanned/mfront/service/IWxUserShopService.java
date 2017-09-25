package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopOrder;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/1 14:57.
 */
public interface IWxUserShopService {

    /**
     * h5微商城首页
     * @param param
     * @return
     */
    JSONObject selectGoodsList(String param) throws Exception;
    /**
     * 提交订单
     * @param param
     * @return
     */
    ShopOrder submitOrder(InputParameter param) throws Exception;

    /**
     * 支付订单
     * @param shopOrder
     * @return
     */
    JSONObject payOrder(ShopOrder shopOrder) throws Exception;

    /**
     * 微信回调
     * @param param
     * @return
     */
    void callBack(String param) throws Exception;

    /**
     * 用户购买记录
     * @return
     */
    JSONObject buyRecord(String shopCode) throws Exception;

    /**
     * 用户的一些基本信息
     * @return
     */
    JSONObject userInfo() throws Exception;

}
