package com.har.unmanned.mfront.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * http请求
 */
public class HttpUtil {

    public static String post(String url, String reqParam) throws Exception {
        HttpClient httpClient = new HttpClient();

        PostMethod method = new PostMethod(url);

        method.getParams().setParameter("http.protocol.content-charset",
                "UTF-8");
        method.addRequestHeader("http.protocol.content-charset", "UTF-8");

        StringRequestEntity entity = new StringRequestEntity(reqParam);

        method.setRequestEntity(entity);

        httpClient.executeMethod(method);

        String respResult = method.getResponseBodyAsString();

        method.releaseConnection();

        return respResult;
    }
}
