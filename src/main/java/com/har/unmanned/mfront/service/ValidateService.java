package com.har.unmanned.mfront.service;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.validate.InputParameter;

public interface ValidateService {
    /**
     * 发送验证码
     *
     * @param reqParam
     * @throws Exception
     */
    void sendValidateCode(JSONObject reqParam) throws Exception;

    /**
     * 验证验证码
     *
     * @param reqParam
     * @throws Exception
     */
    void checkValidateCode(JSONObject reqParam) throws Exception;

    /**
     * 初始化权限验证
     * @return
     * @throws Exception
     */
    JSONObject permissionsValidation() throws Exception;

    /**
     * 绑定手机号
     * @param inputParameter
     * @return
     * @throws Exception
     */
    JSONObject bindingPhone(InputParameter inputParameter) throws Exception;
}
