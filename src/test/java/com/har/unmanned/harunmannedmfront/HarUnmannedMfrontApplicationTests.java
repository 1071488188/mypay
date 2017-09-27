package com.har.unmanned.harunmannedmfront;

import com.har.unmanned.mfront.utils.*;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

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
        param.put("out_trade_no", "20170921192631363840726520");
        param.put("total_fee", "120");
        param.put("spbill_create_ip", "192.168.70.5");
        param.put("notify_url", "http:///har-unmanned-mfront/api/v1/wxUserShop/wxPayCallBack");
        param.put("trade_type", "JSAPI");
        param.put("openid", "ofSmLt-EwP8qZfdtqKagbNVlMIGM");
        String sign = new WeiXinUtils().createPackage(param, "daeb1993ddee0822edcbeeffa35dedcf");
        param.put("sign", sign);
        String respString = WxHttpUtil.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", param, "utf-8");
        Map<String, String> map = XMLUtil.doXMLParse(respString);
        System.out.println(map);
    }

    @Test
    public void testSign() throws Exception {
        WeiXinUtils weiXinUtils = new WeiXinUtils();
        String sing = "<xml><appid><![CDATA[wx6b09d460cd913b96]]></appid>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1303699301]]></mch_id>\n" +
                "<nonce_str><![CDATA[1579779b98ce9edb98dd85606f2c119d]]></nonce_str>\n" +
                "<openid><![CDATA[ofSmLt7PC7oMaculqxBSsuaKW4ww]]></openid>\n" +
                "<out_trade_no><![CDATA[20170927093208775562393362]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[75332E5D9896F83AB5B2EC59FB4B877A]]></sign>\n" +
                "<time_end><![CDATA[20170927093053]]></time_end>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000017201709274495565678]]></transaction_id>\n" +
                "</xml>";
        TreeMap map = (TreeMap) XMLUtil.doXMLParse(sing);
        boolean validSign = weiXinUtils.isValidSign(map, "daeb1993ddee0822edcbeeffa35dedcf");
        System.out.println(validSign);
    }

    @Test
    public void testEncode() throws Exception {
        String s = "陈超";
        byte[] encode = Base64Utils.encode(s.getBytes("utf-8"));
        System.out.println(new String(encode, "utf-8"));
    }
}
