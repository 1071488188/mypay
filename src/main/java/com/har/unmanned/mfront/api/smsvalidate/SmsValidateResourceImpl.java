package com.har.unmanned.mfront.api.smsvalidate;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.smsvalidate.ValidGroup.SendValidateGroup;
import com.har.unmanned.mfront.service.SecurityCodeService;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信接口
 */
@Slf4j
@RestController
@RequestMapping(value = "/sms", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SmsValidateResourceImpl implements SmsValidateResource {
    @Autowired
    private SecurityCodeService securityCodeService;

    @Override
    @PostMapping("/sendValidateCode")
    public JSONObject sendValidateCode(@Validated({SendValidateGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        log.info("{},{}", "发送短信请求参数", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "发送短信_开始", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();
        //分装发送验证码请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("mobile", inputParameter.getPhone());//手机号
        reqParam.put("content", inputParameter.getContent());//短信内容
        reqParam.put("opt_type", inputParameter.getOptType());//发送短信操作类型

        log.info("{},{}", "发送短信请求参数", reqParam);
        LogHelper.save(LogType.REQUEST, "发送短信请求参数", reqParam);
        securityCodeService.sendValidateCode(reqParam);

        log.info("{},{}", "发送短信请求成功", reqParam);
        LogHelper.save(LogType.RESPONSE, "发送短信响应", null);

        return respMessage.getRespMessage();
    }

    @Override
    @PostMapping("/checkValidateCode")
    public JSONObject checkValidateCode(@Validated({SendValidateGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        log.info("{},{}", "检查校验码请求参数", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "检查校验码_开始", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();
        //分装发送验证码请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("mobile", inputParameter.getPhone());// 手机号
        reqParam.put("validateCode", inputParameter.getValidateCode());// 校验码

        log.info("{},{}", "检查校验码请求参数", reqParam);
        LogHelper.save(LogType.REQUEST, "检查校验码请求参数", reqParam);
        securityCodeService.checkValidateCode(reqParam);

        log.info("{},{}", "检查校验码请求成功", reqParam);
        LogHelper.save(LogType.RESPONSE, "检查校验码响应", null);

        return respMessage.getRespMessage();
    }

}
