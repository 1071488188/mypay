package com.har.unmanned.mfront.api.dispatch;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjj
 * @create 2017-09-19 14:57
 **/
@Slf4j
@RestController
@RequestMapping(value = "/smsValidate", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class DispatchResourceImpl implements DispatchResource{
    @Override
    @GetMapping("/dispatchList")
    public JSONObject dispatchList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize)throws Exception {
        return null;
    }

    @Override
    public JSONObject updateDispatchStatus(JSONObject params)throws Exception {
        return null;
    }

    @Override
    public JSONObject replenishmentList(JSONObject params)throws Exception {
        return null;
    }

    @Override
    public JSONObject confirmReplenishment(JSONObject params)throws Exception {
        return null;
    }
}
