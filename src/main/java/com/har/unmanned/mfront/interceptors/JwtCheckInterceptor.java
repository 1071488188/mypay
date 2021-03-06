package com.har.unmanned.mfront.interceptors;


import com.har.bigdata.thread.ThreadLocalCache;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.utils.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT校验拦截器
 */
@Slf4j
@Component
public class JwtCheckInterceptor implements HandlerInterceptor {

    @Value("${client.client-secret}")
    String clientSecret;
    @Value("${client.APPID}")
    String APPID;
    @Autowired
    WxAuthUtil wxAuthUtil;

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("End:" + request.getRequestURI());
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle:" + request.getRequestURI());
    }

    @ResponseBody
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Start:" + request.getRequestURI());
        String theNameOfTheModule = UrlUtil.getSubUrl(3);//获取当前url的第三级作为模块名称存日志
        // 返回消息
        RespMessage respMessage = new RespMessage();
        // 将cookie中取出的token转换成OAuth2格式
        String cookieStr = (String) CookieUtil.getCookieByName(request, Constants.ACCESS_TOKEN);
        ThreadLocalCache.getInstance().setCache(APPID, Constants.Topic, theNameOfTheModule, null, ContextHolderUtils.getIp());//日志系统存日志
        log.info("=====获取到的cookie=====" + cookieStr);
        if (StringUtils.isNotBlank(cookieStr)) {
            log.debug("{}", cookieStr);
            // TODO 验签，只要解析成功，即表示验签通过
            Object body = null;
            try {
                body = Jwts.parser().setSigningKey(clientSecret.getBytes()).parse(cookieStr).getBody();
            } catch (Exception e) {
                if (e instanceof ExpiredJwtException) {
                    log.error("检查Cookie过期，请重新授权");
                    throw new ApiBizException(ErrorCode.E00000006.CODE, ErrorCode.E00000006.MSG, cookieStr);
                }
                e.printStackTrace();
            }

            DefaultClaims claims = (DefaultClaims) body;

            Object openId = claims.get("openId");

            if (!CheckUtil.isNull(theNameOfTheModule)) {
                ThreadLocalCache.getInstance().setCache(APPID, Constants.Topic, theNameOfTheModule, openId + "", ContextHolderUtils.getIp());//日志系统存日志
            }
            if (CheckUtil.isNull(openId)) {
                log.error("未获取到openid");
                throw new ApiBizException(ErrorCode.E00000006.CODE, ErrorCode.E00000006.MSG, cookieStr);
            }
        } else {
            log.error("检查Cookie为空，请授权");
            throw new ApiBizException(ErrorCode.E00000006.CODE, ErrorCode.E00000006.MSG, cookieStr);
        }

        return true;
    }
}
