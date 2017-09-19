package com.har.unmanned.mfront.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@Component
public class RequestParamUtil {

    /**
     * 获取request请求中参数
     *
     * @param request
     * @return json格式数据
     * @throws UnsupportedEncodingException
     */
    public static JSONObject getRequestParam2Json(HttpServletRequest request) throws UnsupportedEncodingException {
        JSONObject param = new JSONObject();

        Enumeration<?> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();

            String value = request.getParameter(paraName);
            param.put(paraName, value);
        }

        return param;
    }

    /**
     * 获取request请求中参数
     *
     * @param request
     * @throws UnsupportedEncodingException
     * @return String类型数据
     */
    public static String getRequestParam2String(HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuffer url = new StringBuffer();

        Enumeration<?> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();

            String paraNameStr = new String(request.getParameter(paraName).getBytes("ISO8859-1"), "UTF-8");
            url.append("&").append(paraName).append("=").append(paraNameStr);
        }

        return url.toString();
    }

}
