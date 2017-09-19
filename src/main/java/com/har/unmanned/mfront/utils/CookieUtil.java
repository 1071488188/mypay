package com.har.unmanned.mfront.utils;


import com.har.unmanned.mfront.exception.ApiBizException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CookieUtil {
    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie Key
     * @return  value
     */
    public static Object getCookieByName(HttpServletRequest request, String name) throws ApiBizException {
        try {
            Map<String, Object> cookieMap = ReadCookieMap(request);

            if (cookieMap.containsKey(name)) {
                return cookieMap.get(name);
            }
        } catch (Exception e) {
            log.error("获取Cookie信息失败" + e);
        }

        return "";
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Object> ReadCookieMap(HttpServletRequest request) {
        Map<String, Object> cookieMap = new HashMap<String, Object>();

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
        }

        return cookieMap;
    }
}
