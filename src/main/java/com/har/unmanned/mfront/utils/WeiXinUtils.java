package com.har.unmanned.mfront.utils;


import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.impl.RedisServiceImpl;
import com.har.unmanned.mfront.wxapi.fixed.WxTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

@Slf4j
@Component
public class WeiXinUtils {
	@Value("${wx.pay.charset}")
	private String charset;
	@Value("${wx.pay.ticketUrl}")
	private String ticketUrl;
	@Autowired
	private RedisServiceImpl redisService;
	@Autowired
	private WxTokenService wxTokenService;
	/**
	 * 创建支付包Package
	 * @param treeMap
	 * @return
	 */
	public String createPackage(TreeMap<String,String> treeMap , String paternerKey){
		String string1=originalString(treeMap);
		String stringSignTemp = string1 + "key="+paternerKey;
		System.out.println(stringSignTemp);
		String sign=Md5Util.MD5Encode(stringSignTemp, charset).toUpperCase();
		return sign;
	}
	/**
	 * 签名算法
	 * @param treeMap
	 * @return
	 */
	public String createSign(TreeMap<String,String> treeMap, String paternerKey){
		String string1=originalString(treeMap);
		String stringSignTemp = string1 + "key="+paternerKey;
		System.out.println(stringSignTemp);
		String sign=Md5Util.MD5Encode(stringSignTemp, charset).toUpperCase();
		return sign;
	}
	/**
	 * 创建支付签名paysign
	 * @param
	 * @return
	 */
	public String createPaySign(TreeMap<String,String> param, String paternerKey){
		String string1=originalString(param);
		String stringSignTemp = string1 + "key="+paternerKey;
		System.out.println("签名调试输出："+stringSignTemp);
		String paysign=Md5Util.MD5Encode(stringSignTemp, charset).toUpperCase();
		return paysign;
	}
	/**
	 * wxpackage组装原始串
	 * @param treeMap
	 * @return
	 */
	@SuppressWarnings("null")
	private String originalString(TreeMap<String,String> treeMap){
		Set<Entry<String, String>> entry = treeMap.entrySet();
		StringBuffer sb = new StringBuffer();
		for(Entry<String,String> obj : entry){
			String k = obj.getKey();
			String v = obj.getValue();
			if(v == null && v.equals(""))
				continue;
			sb.append(k+"="+v+"&");
		}
		return sb.toString();
	}
	@SuppressWarnings({ "unused", "null" })
	private String originalURLString(TreeMap<String,String> treeMap){
		Set<Entry<String, String>> entry = treeMap.entrySet();
		StringBuffer sb = new StringBuffer();
		try {
			for (Entry<String, String> obj : entry) {
				String k = obj.getKey();
				String v = obj.getValue();
				if (v == null && v.equals(""))
					continue;
				sb.append(k.toLowerCase() + "=" + URLEncoder.encode(v, charset) + "&");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sb.toString();
	}
	/**
	 * 创建微信支付订单号
	 * @return
	 */
	public String getOutTradeNo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String body=String.valueOf((int)(Math.random()*100000000));
		String outTradeNo="WXP"+sdf.format(new Date())+body;
		System.out.println("创建支付订单号："+outTradeNo);
		return outTradeNo;
	}
	/**
	 * 创建格式为yyyyMMddHHmmss的当前时间串
	 * @return
	 */
	public String getNowTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	public static void main(String[] args) {
		
	}
	/**
	 * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	@SuppressWarnings("null")
	public synchronized boolean isValidSign(TreeMap<String,String> treeMap, String paternerKey){
		Set<Entry<String, String>> entry = treeMap.entrySet();
		StringBuffer sb = new StringBuffer();
		String signback = null;
		String input_charset=null;
		for(Entry<String,String> obj : entry){
			String k = obj.getKey();
			String v = obj.getValue();
			if(v == null && v.equals(""))
				continue;
			if(k.equals("sign")){
				signback = v;
				continue;
			}
			sb.append(k.toLowerCase()+"="+v+"&");
		}
		String string1=sb.toString();
		String stringSignTemp = string1 + "key="+paternerKey;
		System.out.println("程序计算签名串："+stringSignTemp);
		String sign=Md5Util.MD5Encode(stringSignTemp, input_charset).toUpperCase();
		System.out.println("程序计算财付通签名："+sign);
		System.out.println("系统返回签名："+signback);
		if(sign.equals(signback)){
			System.out.println("DeBug财付通签名比对结果：TRUE");
			return true;
		}else{
			System.out.println("DeBug财付通签名比对结果：FALSE");
			return false;
		}
	} 
	/**
	 * 判断微信签名
	 * @param treeMap
	 * @return
	 */
	@SuppressWarnings("null")
	public synchronized boolean isWXSign(TreeMap<String,String> treeMap){
		Set<Entry<String, String>> entry = treeMap.entrySet();
		StringBuffer sb = new StringBuffer();
		String appSignature = null;
		for(Entry<String,String> obj : entry){
			String k = obj.getKey();
			String v = obj.getValue();
			if(v == null && v.equals(""))
				continue;
			if(k.equals("AppSignature")){
				appSignature = v;
				continue;
			}
			if(k.equals("SignMethod")){
				continue;
			}
			sb.append(k.toLowerCase()+"="+v+"&");
		}
		String paysign = sb.toString();
		paysign = paysign.substring(0, paysign.length()-1);
		System.out.println("\n\n程序计算微信签名串："+paysign);
		paysign = Sha1Util.getSha1(paysign);
		System.out.println("程序计算微信签名结果："+paysign);
		System.out.println("微信返回签名结果："+appSignature);
		if(paysign.equals(appSignature)){
			System.out.println("DeBug微信签名比对结果：TRUE");
			return true;
		}else{
			System.out.println("DeBug微信签名比对结果：FALSE");
			return false;
		}
	}
	public TreeMap<String,String> mapToTreeMap(Map<Object,Object> map){
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		Set<Entry<Object, Object>> entry = map.entrySet();
		for (Entry<Object, Object> key : entry) {
			treeMap.put(key.getKey().toString(), ((String[])key.getValue())[0].toString());
		}
		return treeMap;
	}
	public TreeMap<String,String> strmapToTreeMap(Map<String,String> map){
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		Set<Entry<String, String>> entry = map.entrySet();
		for (Entry<String, String> key : entry) {
			treeMap.put(key.getKey().toString(), key.getValue().toString());
		}
		return treeMap;
	}

	public String getTicket(String accessToken) throws ApiBizException {
		log.info("获取ticket传入参数: {}", accessToken);
		String ticket = redisService.get(Constants.WX_TICKET);
		if (CheckUtil.isNull(ticket)) {
			String s = synchronizationGetTicket(accessToken);
			return s;
		}
		return ticket;
	}

	private synchronized String synchronizationGetTicket(String accessToken) throws ApiBizException {
		String ticket_url = MessageFormat.format(ticketUrl, new String[]{accessToken});
		log.info("请求ticket的url: {}", ticket_url);
		String s = WxHttpUtil.sendGet(ticket_url, "utf-8");
		log.info("请求ticket返回数据: {}", s);
		// 如果请求成功
		String ticket;
		JSONObject jsonObject = JSONObject.parseObject(s);
		if (null != jsonObject && "0".equals(jsonObject.getString("errcode"))) {
			ticket = jsonObject.getString("ticket");
			log.info("请求到的ticket实际数据: {}", ticket);
			redisService.put(Constants.WX_TICKET, ticket, 5400);
		} else if (null != jsonObject && "40001".equals(jsonObject.getString("errcode"))){ // token因为未知原因失效
			String token = wxTokenService.synchronizationGetToken(); // 重新获取token
			ticket = this.getTicket(token); // 再次获取ticket
		} else {
			throw new ApiBizException(ErrorCode.E00000001.CODE, ErrorCode.E00000001.MSG, accessToken);
		}
		return ticket;
	}

	final static String KEYSTORE_FILE = "E:/apiclient_cert.p12";//支付证书地址
	final static String KEYSTORE_PASSWORD = "1303699301";//证书相应密钥
}
