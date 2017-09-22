package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.service.SecurityCodeService;
import com.har.unmanned.mfront.utils.ApiRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecurityCodeServiceImpl implements SecurityCodeService {
    private static final Logger log = LoggerFactory.getLogger(SecurityCodeServiceImpl.class);

    @Override
    public void sendValidateCode(JSONObject reqParam) throws Exception {
        log.info("发送验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("validate_type", 1);// 生成验证码的类型
        param.put("content", reqParam.getString("content"));// 发送内容
        param.put("opt_type", reqParam.getString("opt_type"));// 发送短信操作类型
        ApiRequestClient.post(param, "/login/validate/HF50001");
    }

    @Override
    public void checkValidateCode(JSONObject reqParam) throws Exception {
        log.info("验证验证码ServiceImpl传入参数：" + reqParam);
        //传入参数
        JSONObject param = new JSONObject();
        param.put("phone_number", reqParam.getString("mobile"));// 手机号
        param.put("validate_type", 1);//生成验证码的类型
        param.put("validate_code_input", reqParam.getString("validateCode"));// 验证码
        ApiRequestClient.post(param, "/login/validate/HF50002");
    }
}
