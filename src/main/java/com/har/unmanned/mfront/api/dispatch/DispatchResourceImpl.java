package com.har.unmanned.mfront.api.dispatch;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.service.DispatchService;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    DispatchService dispatchService;

    @Override
    @GetMapping("/dispatchList")
    public JSONObject dispatchList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "status") String status)throws Exception {
        LogHelper.save(LogType.RECEIVE, "配送中心列表_开始", null);
        log.info("param={}, {}", page, pageSize);
        // 返回消息
        RespMessage respMessage = new RespMessage();
        // 返回数据
        JSONObject retJson;
        // 请求参数
        JSONObject reqParam = new JSONObject();
        reqParam.put("page", page);
        reqParam.put("pageSize", pageSize);
        reqParam.put("status", status);

        LogHelper.save(LogType.REQUEST, "配送中心列请求参数", reqParam);
        log.info("配送中心列请求参数："+ reqParam);
        retJson = this.dispatchService.dispatchList(reqParam);

        LogHelper.save(LogType.RESPONSE, "配送中心列表响应", retJson);
        log.info("配送中心列表响应："+retJson);
        respMessage.setRespCode(ErrorCode.E00000000.CODE);
        respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        respMessage.setData(retJson);
        return respMessage.getRespMessage();
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