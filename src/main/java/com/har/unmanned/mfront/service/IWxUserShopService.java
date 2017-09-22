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
public abstract class IWxUserShopService {

    /**
     * h5微商城首页
     * @param param
     * @return
     */
    public abstract JSONObject selectGoodsList(String param) throws ApiBizException;
    /**
     * 提交订单
     * @param param
     * @return
     */
    public abstract ShopOrder submitOrder(InputParameter param) throws ApiBizException, UnsupportedEncodingException;

    /**
     * 支付订单
     * @param shopOrder
     * @return
     */
    public abstract JSONObject payOrder(ShopOrder shopOrder) throws Exception;

    /**
     * 微信回调
     * @param param
     * @return
     */
    public abstract void callBack(String param) throws Exception;

    /**
     * 用户购买记录
     * @return
     */
    public abstract JSONObject buyRecord() throws ApiBizException;

    /**
     * 用户的一些基本信息
     * @return
     */
    public abstract JSONObject userInfo() throws ApiBizException, UnsupportedEncodingException;

}
