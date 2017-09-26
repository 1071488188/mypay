package com.har.unmanned.mfront.api.validate;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.validate.ValidGroup.BindingPhoneGroup;
import com.har.unmanned.mfront.api.validate.ValidGroup.SendValidateGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.ValidateService;
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
 * 短信接口验证
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/validation", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ValidateResourceImpl implements ValidateResource {
    @Autowired
    private ValidateService securityCodeService;

    @Override
    @PostMapping("/sendValidateCode")
    public JSONObject sendValidateCode(@Validated({SendValidateGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        log.info("{},{}", "发送短信请求参数", JSONObject.toJSON(inputParameter));
        LogHelper.save(LogType.RECEIVE, "发送短信_开始", JSONObject.toJSON(inputParameter));
        // 返回消息
        RespMessage respMessage = new RespMessage();
        //分装发送验证码请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("mobile", inputParameter.getCellPhoneNumber());//手机号

        log.info("{},{}", "发送短信请求参数", reqParam);
        LogHelper.save(LogType.REQUEST, "发送短信请求参数", reqParam);
        securityCodeService.sendValidateCode(reqParam);

        log.info("{},{}", "发送短信请求成功", reqParam);
        LogHelper.save(LogType.RESPONSE, "发送短信响应", null);

        return respMessage.getRespMessage();
    }

    /**
     * 初始化权限验证
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject permissionsValidation() throws Exception {
        log.info("------------------初始化权限验证开始-------------------------------");
        securityCodeService.permissionsValidation();
        log.info("------------------初始化权限验证结束-------------------------------");
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,null).getRespMessage();
    }

    /**
     * 绑定手机号
     * @param inputParameter
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject bindingPhone(@Validated({BindingPhoneGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        log.info("------------------绑定手机号开始-------------------------------");
        JSONObject jsonObject=securityCodeService.bindingPhone(inputParameter);
        log.info("------------------绑定手机号结束-------------------------------");
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage();
    }
}
