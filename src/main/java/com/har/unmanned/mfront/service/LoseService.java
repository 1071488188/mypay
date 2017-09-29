package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.exception.ApiBizException;

/**
 * @author huangjj
 * @create 2017-09-19 16:01
 **/

public interface LoseService {
    /**
     * 货架库存
     * @param param
     * @return
     * @throws Exception
     */
    JSONObject shopStock(JSONObject param) throws ApiBizException;

    /**
     * 货架盘存
     * @param param
     * @return
     * @throws Exception
     */
    void stockLose(JSONObject param) throws ApiBizException;
}
