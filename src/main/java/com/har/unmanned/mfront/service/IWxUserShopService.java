package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.wxUser.InputParameter;
import com.har.unmanned.mfront.exception.ApiBizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
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
    public abstract JSONObject selectGoodsList(String param) throws Exception;
    /**
     * 提交订单
     * @param param
     * @return
     */
    public abstract JSONObject submitOrder(InputParameter param) throws Exception;

    /**
     * 支付订单
     * @param param
     * @return
     */
    public abstract JSONObject payOrder(JSONObject param);

    /**
     * 微信回调
     * @param param
     * @return
     */
    public abstract JSONObject callBack(JSONObject param);

    /**
     * 用户购买记录
     * @return
     */
    public abstract JSONObject buyRecord() throws Exception;

    /**
     * 用户的一些基本信息
     * @return
     */
    public abstract JSONObject userInfo() throws Exception;

}
