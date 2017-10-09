package com.har.unmanned.mfront.utils;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.util.AESUtil;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangjj
 */
@Component
public class ApiRequestClient {

    //老前置机路径
    @Value("${har.frontProxy.url}")
    String FRONT_PROXY_URL;

    //获取配置APPID
    @Value("${har.frontProxy.appId}")
   String APP_ID;

    //获取配置公钥
    @Value("${har.frontProxy.publicKey}")
    String PUBLIC_KEY;


    /**
     * 调用老前置机
     *
     * @param postUrl URl
     * @param reqData 传入参数
     * @return Data:为JSONObject
     * @throws Exception
     */
    public  JSONObject post(JSONObject reqData, String postUrl) throws Exception {
        JSONObject retData = new JSONObject();
        ReqMessage reqMessage = new ReqMessage();
        // 设置AppId
        reqMessage.setAppId(APP_ID);
        // 设置MsgKey
        String msgKey = RandomUtils.generateString(16);
        RSAUtil rsaUtil = new RSAUtil();
        rsaUtil.loadPublicKey(PUBLIC_KEY);
        reqMessage.setMsgKey(rsaUtil.encrypt(msgKey));
        String data = AESUtil.getInstance(msgKey).encrypt(reqData.toJSONString());
        reqMessage.setData(data);
        // --------------------------------------以上代码为封装请求报文---------------------------------------------------

        // 请求返回
        String resp = ApiRequestClient.httpPost(FRONT_PROXY_URL + postUrl, reqMessage.getReqMessage().toJSONString());
        // --------------------------------------以下代码为解析响应报文---------------------------------------------------
        JSONObject respData = CheckUtil.isNull(resp) ? new JSONObject() : JSONObject.parseObject(resp);
        if (!CheckUtil.isNull(respData.getString("RespCode")) && ErrorCode.E00000000.CODE.equals(respData.getString("RespCode"))) {
            String decryptData = AESUtil.getInstance(msgKey).decrypt(respData.getString("Data"));
            retData = JSONObject.parseObject(decryptData);
        } else {
            throw new ApiBizException(respData.getString("RespCode"), respData.getString("RespDesc"), retData);
        }
        return retData;
    }

    public static String httpPost(String url, String reqParam) throws Exception {
        HttpClient httpClient = new HttpClient();

        PostMethod method = new PostMethod(url);

        method.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        method.addRequestHeader("http.protocol.content-charset", "UTF-8");

        RequestEntity entity = new StringRequestEntity(reqParam);

        method.setRequestEntity(entity);

        httpClient.executeMethod(method);

        String respResult = method.getResponseBodyAsString();

        method.releaseConnection();

        return respResult;
    }
}
