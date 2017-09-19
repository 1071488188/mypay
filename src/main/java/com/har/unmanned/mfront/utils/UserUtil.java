package com.har.unmanned.mfront.utils;


import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 用户信息工具类
 */
@Slf4j
@Component
public class UserUtil {

    @Value("${client.client-secret}")
    String key;

    /***
     * 从cookie中获取用户信息
     * @return 授权登录后的用户信息
     */
    public ShopWechat userInfo() throws ApiBizException {
        // 初始化返回用户信息
        ShopWechat wxUser = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 将cookie中取出的token转换成OAuth2格式
            String cookieStr = (String) CookieUtil.getCookieByName(request, Constants.ACCESS_TOKEN);
//            log.info("=====获取到的cookie=====" + cookieStr);
            if (StringUtils.isNotBlank(cookieStr)) {
                // TODO 验签，只要解析成功，即表示验签通过
                Object body = Jwts.parser().setSigningKey(key.getBytes()).parse(cookieStr).getBody();
                DefaultClaims claims = (DefaultClaims) body;
                wxUser = new ShopWechat();
                Set<String> set = claims.keySet();
                for (String key : set) {
                    if (key.contains("openId")) {
                        wxUser.setOpenid((String) claims.get(key));
                        break;
                    }
                }
            } else {
                log.info("获取用户信息失败，用户未授权");
                throw new ApiBizException(ErrorCode.E00000006.CODE, ErrorCode.E00000006.MSG, cookieStr);
            }
        } catch (Exception e) {
            log.info("获取用户信息失败，验签异常：" + e);
            throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, e);
        }
        return wxUser;
    }

}
