package com.har.unmanned.mfront.api.validate;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.validate.ValidGroup.BindingPhoneGroup;
import com.har.unmanned.mfront.api.validate.ValidGroup.PermissionsGroup;
import com.har.unmanned.mfront.api.validate.ValidGroup.SendValidateGroup;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.ValidateService;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.aop.ControlLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/sendValidate")
    @ControlLog("发送验证码")
    public JSONObject sendValidateCode(@Validated({SendValidateGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        // 返回消息
        RespMessage respMessage = new RespMessage();
        //分装发送验证码请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("mobile", inputParameter.getCellPhoneNumber());//手机号
        securityCodeService.sendValidateCode(reqParam);

        return respMessage.getRespMessage();
    }

    /**
     * 初始化权限验证
     * @return
     * @throws Exception
     */
    @Override
    @GetMapping("/permissionsValidation")
    @ControlLog("初始化权限验证")
    public JSONObject permissionsValidation(@Validated({PermissionsGroup.class})InputParameter inputParameter) throws Exception {
        JSONObject jsonObject=securityCodeService.permissionsValidation(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage();
    }

    /**
     * 绑定手机号
     * @param inputParameter
     * @return
     * @throws Exception
     */
    @Override
    @PostMapping("/bindingPhone")
    @ControlLog("绑定手机号")
    public JSONObject bindingPhone(@Validated({BindingPhoneGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        JSONObject jsonObject=securityCodeService.bindingPhone(inputParameter);
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,jsonObject).getRespMessage();
    }
}
