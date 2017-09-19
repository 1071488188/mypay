package com.har.unmanned.mfront.utils;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.har.unmanned.mfront.config.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RespMessage {

    private Object data;

    private String respCode = ErrorCode.E00000000.CODE;

    private String respDesc = ErrorCode.E00000000.MSG;

    public RespMessage() {}

    public RespMessage(Object data) {
        this.data = data;
    }

    public RespMessage(String respCode, String respDesc, Object data) {
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.data = data;
    }

    public static final String RESP_CODE = "RespCode";

    public static final String RESP_DESC = "RespDesc";

    public Map<String, Object> toMap() {
        Map<String, Object> msg = Maps.newHashMap();
        msg.put(RESP_CODE, respCode);
        msg.put(RESP_DESC, respDesc);
        msg.put("Data", data);
        return msg;
    }

    public JSONObject getRespMessage() {

        JSONObject message = new JSONObject();

        message.put("RespCode", respCode);
        message.put("RespDesc", respDesc);
        message.put("Data", data);

        return message;
    }
}
