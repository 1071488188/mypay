package com.har.unmanned.mfront.api.smsvalidate;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Override
    @PostMapping("/sendValidate")
    public JSONObject sendValidate(JSONObject params) throws Exception {

        return null;
    }

    @Override
    @PostMapping("/validateCode")
    public JSONObject validateCode(JSONObject params) throws Exception {
        return null;
    }
}
