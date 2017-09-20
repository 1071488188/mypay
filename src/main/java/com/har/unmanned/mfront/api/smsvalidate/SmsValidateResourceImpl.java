package com.har.unmanned.mfront.api.smsvalidate;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.api.smsvalidate.ValidGroup.SendValidateGroup;
import com.har.unmanned.mfront.config.CodeConstants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.SecurityCodeService;
import com.har.unmanned.mfront.utils.RespMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjj
 * @create 2017-09-19 10:10
 **/
@Slf4j
@RestController
@RequestMapping(value = "/smsValidate", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SmsValidateResourceImpl implements SmsValidateResource {
    @Autowired
    private SecurityCodeService securityCodeService;
    //短信模板
    @Value("${har.sms.template}")
    public static String template;

    @Override
    @PostMapping("/sendValidate")
    public JSONObject sendValidate(@Validated({SendValidateGroup.class}) @RequestBody InputParameter inputParameter) throws Exception {
        LogHelper.save(LogType.RECEIVE, "发送短信_开始", null);
        log.info("param={}", inputParameter);
        // 返回消息
        RespMessage respMessage = new RespMessage();
        //分装发送验证码请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("mobile", inputParameter.getMobile());//手机号
        reqParam.put("content", template);//短信内容
        reqParam.put("opt_type", CodeConstants.SmsOptType.UNMANNED);//发送短信操作类型
        LogHelper.save(LogType.REQUEST, "发送短信请求参数", reqParam);
        log.info("发送短信请求参数：" + reqParam);
        securityCodeService.sendValidate(reqParam);

        LogHelper.save(LogType.RESPONSE, "发送短信响应", null);
        log.info("发送短信响应：" + null);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        return respMessage.getRespMessage();
    }

}
