package com.har.unmanned.mfront.utils;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.model.ShopWechat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 微信授权工具类
 */
@Slf4j
@Component
public class WxAuthUtil {

    @Value("${har.server.web.errorPath}")
    public String errorPath;

    @Value("${har.server.web.host}")
    public String host;

    @Value("${wx.auth.authCheckAccessTokenUrl}")
    public String authCheckAccessTokenUrl;

    @Value("${wx.auth.authRefreshTokenUrl}")
    public String authRefreshTokenUrl;

    @Value("${wx.auth.authAccessTokenUrl}")
    public String authAccessTokenUrl;

    @Value("${wx.auth.authPath}")
    public String authRedirectUrl;

    @Value("${wx.auth.appId}")
    public String wxAppId;

    @Value("${wx.auth.secret}")
    public String secret;

    public String index = "/#/index";


    /**
     * 页面加载时获取微信验证相关参数
     *
     * @param url
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getWxSignPar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getWxSignPar(HttpServletRequest request, String url) throws Exception {
        RespMessage respMessage = new RespMessage();

        try {
//			JSONObject resp = this.harCoreService.getSignatureByUrl(url);

//			log.info("页面加载时获取微信验证相关返回数据：" + resp);
//			respMessage.setData(resp.toJSONString());
            respMessage.setRespCode(ErrorCode.E00000000.CODE);
            respMessage.setRespDesc(ErrorCode.E00000000.MSG);
        } catch (Exception e) {
            log.info("页面加载时获取微信验证相关返回数据异常：" + e);
            respMessage.setRespCode(ErrorCode.E00000001.CODE);
            respMessage.setRespDesc(e.getMessage());
        }

        return respMessage.getRespMessage().toJSONString();
    }

    /***
     * 获取微信授权地址
     * @param scopr 授权类型
     * @param currentAddress    重定向地址
     * @return
     */
    public String getAuthUrl(String scopr, String currentAddress) {
        return MessageFormat.format(authRedirectUrl, new String[]{scopr, currentAddress});
    }

    /***
     * 获取用户信息
     * @param code  微信授权码
     * @return
     */
    public ShopWechat getInfoOrOpenId(String code) throws ApiBizException {
        log.info("授权成功取得code：" + code);

        ReqMessage reqMessage = new ReqMessage();
        ShopWechat wxUser = new ShopWechat();

        if (!CheckUtil.isNull(code)) {

            try {
                // 1、跳转URL，通过code换取网页授权access_token
                String codeUrl = MessageFormat.format(authAccessTokenUrl, new String[]{code});
                String jsonObj_access_token = HttpUtil.post(codeUrl, reqMessage.getReqMessage().toJSONString());
                log.info("通过code换取网页授权access_token响应报文：" + jsonObj_access_token);
                JSONObject jsonObjAccessToken = JSONObject.parseObject(jsonObj_access_token);

                //2、得到openId
                String openId = jsonObjAccessToken.getString("openid");
                if (CheckUtil.isNull(openId)) {
                    throw new ApiBizException(ErrorCode.E00000010.CODE, ErrorCode.E00000010.MSG, jsonObjAccessToken);
                }

                //3、得到access_token
                String accessToken = jsonObjAccessToken.getString("access_token");
                if (CheckUtil.isNull(accessToken)) {//如果为空，则刷新access_token

                    // 4、获取刷新token
                    String refresh_token = jsonObjAccessToken.getString("refresh_token");
                    log.info("获取到的刷新Token：" + refresh_token);

                    // 5、获取刷新token地址
                    String refreshTokenUrl = MessageFormat.format(authRefreshTokenUrl, new String[]{refresh_token});
                    log.info("获取到的刷新token地址：" + refreshTokenUrl);

                    // 5、跳转URL，刷新access_token
                    String jsonObj_access_token_update = HttpUtil.post(refreshTokenUrl, reqMessage.getReqMessage().toJSONString());
                    log.info("刷新access_token响应报文：" + jsonObjAccessToken);

                    // 6、得到新的access_token
                    accessToken = JSONObject.parseObject(jsonObj_access_token_update).getString("access_token");
                    log.info("得到新的accessToken：" + accessToken);
                }

                // 7、获取用户信息地址
                String accessTokenUrl = MessageFormat.format(authCheckAccessTokenUrl, new String[]{accessToken, openId});

                // 8、跳转URL，拉取用户信息
                String userInfo = HttpUtil.post(accessTokenUrl, reqMessage.getReqMessage().toJSONString());
                log.info("获取到用户信息响应报文：" + userInfo);

                // 9、将得到的授权信息封装进对象中
                JSONObject userObj = JSONObject.parseObject(userInfo);
                wxUser.setOpenid(openId);
                wxUser.setName(userObj.getString("nickname"));//昵称
                wxUser.setSex(userObj.getInteger("sex"));//性别
                wxUser.setHeadimgUrl(userObj.getString("headimgurl"));//头像
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取用户信息异常：" + e);
                throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, e);
            }
        }
        return wxUser;
    }

}
