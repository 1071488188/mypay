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
import com.har.unmanned.mfront.wxapi.fixed.WxTokenService;
import com.har.unmanned.mfront.wxapi.templateMsg.MsgDataPojo;
import com.har.unmanned.mfront.wxapi.templateMsg.TempMsgRetrun;
import com.har.unmanned.mfront.wxapi.templateMsg.TemplateMsgPojo;
import com.har.unmanned.mfront.wxapi.templateMsg.TemplateMsgSend;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.TreeMap;


/**
 * 微信相关接口
 * @apiDefine wx 微信相关接口
 * Created by jiang on 2017/9/19.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/wxAuth", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
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
    @Autowired
    WxTokenService wxTokenService;
    @Value("${wx.auth.maxAge}")
    Integer maxAge;
    @Value("${wx.template.subscribe.templateId}")
    private String templateId;
    @Value("${wx.template.subscribe.color}")
    private String color;
    @Value("${wx.template.subscribe.detailsUrl}")
    private String detailsUrl;
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
                   String userName= wxUser.getName();
                    if(!CheckUtil.isNull(userName)){
                        userName= Base64.encodeBase64String(userName.getBytes("UTF-8"));
                        wxUser.setName(userName);
                    }
                    // 保存用户信息
                    shopWechatMapper.insertSelective(wxUser);
                }else{
                    String userName= wxUser.getName();
                    if(!CheckUtil.isNull(userName)){
                        userName= Base64.encodeBase64String(userName.getBytes("UTF-8"));
                        findUser.setName(userName);
                    }
                    findUser.setHeadimgUrl(wxUser.getHeadimgUrl());
                    findUser.setSex(wxUser.getSex());
                    shopWechatMapper.updateByPrimaryKeySelective(findUser);//当用户存在是更新用户信息
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

    /**
     * @api {post} /api/v1/wxAuth/distributionNotice 1.api 配送通知
     * @apiVersion 1.0.0
     * @apiName distributionNotice
     * @apiGroup wx
     * @apiPermission none
     *
     * @apiDescription 配送通知
     *
     * @apiParam {String} title  推送title
     * @apiParam {String} distributionNumber  配送单号
     * @apiParam {String} deliveryTime  配送时间
     * @apiParam {String} distributionNetwork  配送网点
     * @apiParam {String} totalDistribution  配送总计
     * @apiParam {String} openId  微信openId
     * @apiParam {String} remark  备注
     *
     *
     * @apiSuccess (200) {String} RespCode          响应编码，8位
     * @apiSuccess (200) {String} RespDesc          响应描述
     * @apiSuccess (200) {Object} Data			响应数据
     */
    @PostMapping("distributionNotice")
    public JSONObject distributionNotice(@Validated @RequestBody WxImputParam wxImputParam) throws ApiBizException {
        String token = service.get("access_token");
        TemplateMsgPojo templateMsgPojo = new TemplateMsgPojo();
        templateMsgPojo.setUrl(detailsUrl);
        templateMsgPojo.setTemplate_id(templateId);
        templateMsgPojo.setTouser(wxImputParam.getOpenId());
        // templateMsgPojo.setTouser("ofSmLtzfSdB7M-XjUKTu0NDnSJu0");
        Map<String, MsgDataPojo> data = new TreeMap<String, MsgDataPojo>();
        MsgDataPojo first = new MsgDataPojo(wxImputParam.getTitle(), color);
        MsgDataPojo keyword1 = new MsgDataPojo(wxImputParam.getDistributionNumber(), color);
        MsgDataPojo keyword2 = new MsgDataPojo(wxImputParam.getDeliveryTime(), color);
        MsgDataPojo keyword3 = new MsgDataPojo(wxImputParam.getDistributionNetwork(), color);
        MsgDataPojo keyword4 = new MsgDataPojo(wxImputParam.getTotalDistribution(), color);
        MsgDataPojo remark = new MsgDataPojo(wxImputParam.getRemark(), color);
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("keyword3", keyword3);
        data.put("keyword4", keyword4);
        data.put("remark", remark);
        templateMsgPojo.setData(data);
        TempMsgRetrun tempMsgRetrun = new TemplateMsgSend().sendMsg(
                templateMsgPojo, token);
        // 如果是因为token被刷新而导致消息发送失败则拉取一次token重新发送
        if (!tempMsgRetrun.isIssuccess()
                && tempMsgRetrun.getErrmsg().contains("access_token")
                && tempMsgRetrun.getErrmsg().contains("invalid")) {
            wxTokenService.postToken();
            TempMsgRetrun tempMsgRetrun1 = new TemplateMsgSend().sendMsg(templateMsgPojo,
                    service.get("access_token"));
            if(!tempMsgRetrun1.isIssuccess()){
                throw new ApiBizException(ErrorCode.E00000001.CODE,"推送消息失败,订单号为:"+wxImputParam.getDistributionNumber(),tempMsgRetrun.getErrmsg());
            }
        }else if(!tempMsgRetrun.isIssuccess()){
            throw new ApiBizException(ErrorCode.E00000001.CODE,"推送消息失败,订单号为:"+wxImputParam.getDistributionNumber(),tempMsgRetrun.getErrmsg());
        }
        return new RespMessage(ErrorCode.E00000000.CODE,ErrorCode.E00000000.MSG,null).getRespMessage();
    }

}
