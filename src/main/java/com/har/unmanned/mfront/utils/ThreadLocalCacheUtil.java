package com.har.unmanned.mfront.utils;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.thread.ThreadLocalCache;
import com.har.unmanned.mfront.config.Constants;
import org.springframework.beans.factory.annotation.Value;

/**
 * 日志初始化l
 */
public class ThreadLocalCacheUtil {

    // 用户名
    private static String userName = "";
    // IP
    private static String IP = "";
    // appId
    @Value("${client.APPID}") String appId;

    public void init(String param, String logBusinessType) {
        if (!CheckUtil.isNull(param)) {
            JSONObject paramObj = JSONObject.parseObject(param);
            userName = paramObj.getString("userName");
            IP = ContextHolderUtils.getIp();
        }
        ThreadLocalCache.getInstance().setCache(appId, Constants.Topic, logBusinessType, userName, IP);
    }

}
