package com.har.unmanned.mfront.service.impl;

import com.har.bigdata.exception.CommonExceptionLevel;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.WxPayService;
import com.har.unmanned.mfront.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;


@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {
    @Value("${wx.pay.appid}")
    private String appid;  //公众账号ID
    @Value("${wx.pay.appsecret}")
    private String appsecret;
    @Value("${wx.pay.mch_id}")
    private String mch_id;  //商户号
    @Value("${wx.pay.notify_url}")
    private String notify_url;  //后台通知地址
    @Value("${wx.pay.trade_type}")
    private String trade_type;  //交易类型
    @Value("${wx.pay.charset}")
    private String charset;  //编码格式
    @Value("${wx.pay.unified_order_url}")
    private String unified_order_url;  //统一下单地址
    @Value("${wx.pay.close_order_url}")
    private String close_order_url;  //关闭订单地址
    @Value("${wx.pay.query_order_url}")
    private String query_order_url;  //查询订单地址
    @Value("${wx.pay.sign_type}")
    private String sign_type;  //签名类型
    @Autowired
    private WeiXinUtils weiXinUtils;


    @Override
    public Map<String, String> paymentOrderH5(String openid, String total_fee, String body, String out_trade_no, String ip) throws Exception {
        log.info("-------------生成微信签名开始------------");
        String nonceStr = Sha1Util.getNonceStr();
        String timeStamp = DateUtil.getCurrentTimeStamp();
        TreeMap param = new TreeMap();
        param.put("appid", appid);
        param.put("mch_id", mch_id);
        param.put("nonce_str", nonceStr);
        param.put("body", body);
        param.put("out_trade_no", out_trade_no);
        param.put("total_fee", total_fee);
        param.put("spbill_create_ip", ip);
        param.put("notify_url", notify_url);
        param.put("trade_type", trade_type);
        param.put("openid", openid);
        String sign = weiXinUtils.createPackage(param, appsecret);
        param.put("sign", sign);
        String respString = WxHttpUtil.sendPost(unified_order_url, param, charset);
        log.info("微信统一下单返回数据: " + respString);
        Map<String, String> map = XMLUtil.doXMLParse(respString);
        if (!CheckUtil.isEquals(map.get("return_code"), "SUCCESS")) {
            log.error("微信签名失败: " + param);
            throw new ApiBizException(ErrorCode.E00000016.CODE, map.get("return_msg"), param, CommonExceptionLevel.WARN);
        }
        if (!CheckUtil.isEquals(map.get("result_code"), "SUCCESS")) {
            log.error("微信签名失败: " + param);
            throw new ApiBizException(ErrorCode.E00000016.CODE, map.get("err_code_des"), param, CommonExceptionLevel.WARN);
        }
        TreeMap respMap = new TreeMap();
        respMap.put("appId", map.get("appid"));
        respMap.put("timeStamp", timeStamp);
        respMap.put("nonceStr", nonceStr);
        respMap.put("package", "prepay_id=" + map.get("prepay_id"));
        respMap.put("signType", sign_type);
        String paySign = weiXinUtils.createPaySign(respMap, appsecret);
        respMap.put("paySign", paySign);
        log.info("-------------生成微信签名结束------------");
        return respMap;
    }


    @Override
    public Map<String, String> orderQuery(String out_trade_no) throws Exception {
        log.info("-------------查询微信订单开始------------");
        String nonceStr = Sha1Util.getNonceStr();
        TreeMap param = new TreeMap();
        param.put("appid", appid);
        param.put("mch_id", mch_id);
        param.put("out_trade_no", out_trade_no);
        param.put("nonce_str", nonceStr);
        String sign = weiXinUtils.createPackage(param, appsecret);
        param.put("sign", sign);
        String respString = WxHttpUtil.sendPost(query_order_url, param, charset);
        log.info("微信统一下单返回数据: " + respString);
        Map<String, String> map = XMLUtil.doXMLParse(respString);
        if (!CheckUtil.isEquals(map.get("return_code"), "SUCCESS")) {
            log.error("微信订单查询失败: " + param);
            throw new ApiBizException(ErrorCode.E00000017.CODE, map.get("return_msg"), param);
        }
        if (!CheckUtil.isEquals(map.get("result_code"), "SUCCESS")) {
            log.error("微信订单查询失败: " + param);
            throw new ApiBizException(ErrorCode.E00000017.CODE, map.get("err_code_des"), param);
        }
        log.info("-------------查询微信订单结束------------");
        return map;
    }

}
