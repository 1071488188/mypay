package com.har.unmanned.mfront.api.wx;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.api.ApiBaseController;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.dao.ShopWechatMapper;
import com.har.unmanned.mfront.dao.extend.ShopWechatMapperExtend;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import com.har.unmanned.mfront.service.impl.RedisServiceImpl;
import com.har.unmanned.mfront.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;


/**
 * @author zhanggr
 * @apiDefine wxAuth 用户授权相关
 */
@Slf4j
@RestController
@RequestMapping(value = "/wxAuth", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class WxAuthResource extends ApiBaseController {
    @Autowired
    private RedisServiceImpl service;
    @Autowired
    ShopWechatMapper shopWechatMapper;
    @Autowired
    ShopWechatMapperExtend shopWechatMapperExtend;
    @Autowired
    WxAuthUtil wxAuthUtil;
    @Autowired
    JwtUtil jwtUtil;
    @Value("${wx.auth.maxAge}")
    Integer maxAge;
    //保存用户防止重复提交key
    public static String SAVETHEUSERAGAINSTREPEATEDCOMMIT="saveTheUserAgainstRepeatedCommit";

    /***
     * 微信授权回调
     * @param request
     * @param response
     */
    @GetMapping("/wxAuthCallBack")
    public String getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("=====微信回调开始=====");
        log.info("=====微信回调传入参数：" + request.getQueryString());

        // 返回消息
        RespMessage respMessage = new RespMessage();
        // 重定向地址
        String redirectUrl;
        try {
            // 微信回调授权码
            String code = request.getParameter("code");
            log.info("微信回调code:" + code);

            // 微信回调保留参数（暂时作为用户访问链接使用）
            String state = URLDecoder.decode(request.getParameter("state"));
            log.info("微信回调state:" + state);

            if (CheckUtil.isNull(code) || CheckUtil.isNull(state))
                throw new ApiBizException(ErrorCode.E00000012.CODE, ErrorCode.E00000012.MSG, request.getQueryString());

            if (!CheckUtil.isNull(code)) {
                // 根据授权code获取openId
                ShopWechat wxUser = wxAuthUtil.getInfoOrOpenId(code);
                //防止重复提交如果出现重复提交则延长2秒执行
                Object object=service.get(SAVETHEUSERAGAINSTREPEATEDCOMMIT);
                if (!CheckUtil.isNull(object)){
                    Thread.sleep(2000);
                }
                service.put(SAVETHEUSERAGAINSTREPEATEDCOMMIT, wxUser.getOpenid(), 10);
                // 用户是否存在
               ShopWechat findUser= shopWechatMapperExtend.selectByOpenId(wxUser.getOpenid());
                log.info("查询返回用户信息：" + findUser);
                // 拉取授权信息用户
                ShopWechat AccessTokenUser = new ShopWechat();
                AccessTokenUser.setOpenid(wxUser.getOpenid());
                if (CheckUtil.isNull(findUser)) {
                    // 保存用户信息
                    shopWechatMapper.insertSelective(wxUser);
                }
                log.info("微信用户信息：" + JSONObject.toJSON(wxUser));
                // 获取JWT授权码，写入cookie
                String  accessTokenData = jwtUtil.getAccessToken(AccessTokenUser);
                log.info("JWT授权码信息：" + accessTokenData);
                if (!CheckUtil.isNull(accessTokenData)) {
                    // 根据用户信息生成jwt存入cookie
                    CookieUtil.addCookie(response, Constants.ACCESS_TOKEN,accessTokenData, maxAge);// 设置cookie有效期
                    log.info("JWT授权码信息写入Cookie完成");
                }
            }
            log.info("=====微信回调完成=====");
            redirectUrl =state;
            log.info("目标地址：" + redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("=====微信回调异常======：" + e);

            redirectUrl = wxAuthUtil.errorPath;
            // 跳转至错误页面
            log.error("跳转至错误页面" + redirectUrl);
        }

        response.sendRedirect(redirectUrl);
        return "";
    }

}
