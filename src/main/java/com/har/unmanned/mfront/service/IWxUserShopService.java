package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;
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
    public abstract JSONObject selectGoodsList(JSONObject param);
    /**
     * 提交订单
     * @param param
     * @return
     */
    public abstract JSONObject submitOrder(JSONObject param);
}
