package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author huangjj
 * @create 2017-09-19 16:01
 **/

public interface DispatchService {
    /**
     * 配送中心列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    JSONObject dispatchList(JSONObject param) throws Exception;

    /**
     * 更新配送单状态
     *
     * @param param
     * @return
     * @throws Exception
     */
    void updateDispatchStatus(JSONObject param) throws Exception;

    /**
     * 补货列表
     *
     * @param param
     * @return
     * @throws Exception
     */
    JSONObject replenishmentList(JSONObject param) throws Exception;

    /**
     * 确认补货
     *
     * @param param
     * @return
     * @throws Exception
     */
    void confirmReplenishment(JSONObject param) throws Exception;

}
