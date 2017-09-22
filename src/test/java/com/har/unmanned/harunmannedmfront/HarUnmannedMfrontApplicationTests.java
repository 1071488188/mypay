package com.har.unmanned.harunmannedmfront;

import com.har.unmanned.mfront.utils.*;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class HarUnmannedMfrontApplicationTests {

	@Test
	public void contextLoads() throws JDOMException, IOException {
		String nonceStr = Sha1Util.getNonceStr();
		String timeStamp = DateUtil.getCurrentTimeStamp();
		TreeMap param = new TreeMap();
		param.put("appid", "wx6b09d460cd913b96");
		param.put("mch_id", "1303699301");
		param.put("nonce_str", nonceStr);
		param.put("body", "微信支付");
		param.put("out_trade_no", "20170921192631583840726520");
		param.put("total_fee", "120");
		param.put("spbill_create_ip", "192.168.70.5");
		param.put("notify_url", "http:///har-unmanned-mfront/api/v1/wxUserShop/wxPayCallBack");
		param.put("trade_type", "JSAPI");
		param.put("openid", "ofSmLt-EwP8qZfdtqKagbNVlMIGM");
		String sign = WeiXinUtils.createPackage(param, "daeb1993ddee0822edcbeeffa35dedcf");
		param.put("sign", sign);
		String respString = WxHttpUtil.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", param, "utf-8");
		Map<String, String> map = XMLUtil.doXMLParse(respString);
		System.out.println(map);
	}

}
