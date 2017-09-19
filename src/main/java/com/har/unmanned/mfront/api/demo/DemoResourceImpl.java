package com.har.unmanned.mfront.api.demo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiang on 2017/9/18.
 */
@Slf4j
@RestController
@RequestMapping(value = "/demo", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class DemoResourceImpl implements  DemoResource{
    @Override
    @GetMapping("/test")
    public Object demoTest(JSONObject params) {
        return params;
    }
}
