package com.har.unmanned.mfront.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求报文
 *
 * @author tanzeng
 */
public class ReqMessage {

    private String partnerId;

    private String appId;

    private String msgKey;

    private String data;

    private JSONObject dataJson;

    private String devType;

    private String sign;

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JSONObject getDataJson() {
        return dataJson;
    }

    public void setDataJson(JSONObject dataJson) {
        this.dataJson = dataJson;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public JSONObject getReqMessage() {

        JSONObject message = new JSONObject();

        message.put("AppId", appId);
        message.put("PartnerId", partnerId);
        message.put("MsgKey", msgKey);
        message.put("Data", data);
        message.put("Sign", sign);

        return message;
    }
}
