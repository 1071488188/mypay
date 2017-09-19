package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;

public interface SecurityCodeService {
    /**
     * 发送验证码
     *
     * @param reqParam
     * @throws Exception
     */
    void sendValidate(JSONObject reqParam) throws Exception;

    /**
     * 验证验证码
     *
     * @param reqParam
     * @throws Exception
     */
    void validateCode(JSONObject reqParam) throws Exception;
}
