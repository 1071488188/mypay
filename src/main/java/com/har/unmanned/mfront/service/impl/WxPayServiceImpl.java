package com.har.unmanned.mfront.service.impl;

import com.har.unmanned.mfront.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Slf4j
public class WxPayServiceImpl extends WxPayService {
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


	@Override
	public Map<String, String> paymentOrderHbxWeb(String openid, String total_fee, String body, String out_trade_no, String wxCallBack) throws Exception {
		return null;
	}

	@Override
	public Map<String, String> paymentOrderWeb(String openid, String total_fee, String body, String out_trade_no) throws Exception {
		return null;
	}

	@Override
	public boolean closeOrderWeb(String out_trade_no) throws Exception {
		return false;
	}

	@Override
	public Map<String, String> paymentOrderApp(String total_fee, String body, String out_trade_no) throws Exception {
		return null;
	}

	@Override
	public Map<String, String> paymentOrderHSHApp(String total_fee, String body, String out_trade_no, String wxCallBack) throws Exception {
		return null;
	}

	@Override
	public boolean closeOrderApp(String out_trade_no) throws Exception {
		return false;
	}

	@Override
	public Map<String, String> orderquery(String out_trade_no) throws Exception {
		return null;
	}

	@Override
	public Map<String, String> orderQueryH5(String out_trade_no) throws Exception {
		return null;
	}
}
