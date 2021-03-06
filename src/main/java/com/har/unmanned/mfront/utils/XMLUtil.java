package com.har.unmanned.mfront.utils;


import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentHelper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * xml工具类
 * @author miklchen
 *
 */
public class XMLUtil {

	/**
	 * 解析request流对象InputStream
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> doXMLParse(HttpServletRequest request) throws JDOMException, IOException {
		Map<String,String> m = new HashMap<String,String>();
		SAXBuilder builder = new SAXBuilder();
		ServletInputStream in =request.getInputStream();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<Object> list = root.getChildren();
		Iterator<Object> it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<Object> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		return m;
	}
	/**
	 * 解析String类型的xml流对象InputStream
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml 
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map<String,String> doXMLParse(String strxml) throws JDOMException, IOException {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map<String,String> m = new TreeMap<>();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Object> list = root.getChildren();
		Iterator<Object> it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			@SuppressWarnings("unchecked")
			List<Object> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	private static String getChildrenText(List<Object> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<Object> it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				@SuppressWarnings("unchecked")
				List<Object> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	/**
	 * 解析String类型的xml流对象InputStream
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml 
	 * @return
	 * @throws Exception
	 */
	public static JSONObject doXMLParseJson(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
//		Map<String,String> m = new HashMap<String,String>();
		JSONObject json = new JSONObject();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren();
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<?> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenTex(children);
			}
//			m.put(k, v);
			json.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return json;
	}
	/**
	 * 解析String类型的xml流对象InputStream
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml 
	 * @return
	 * @throws Exception
	 */
	public static TreeMap<String, String> doXMLParseTreeMap(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		TreeMap<String,String> m = new TreeMap<String, String>();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren();
		Iterator<?> it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List<?> children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenTex(children);
			}
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	private static String getChildrenTex(List<?> children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator<?> it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List<?> list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenTex(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}		
		return sb.toString();
	}

	public static void main(String[] args) throws JDOMException, IOException {
		String s = "<xml>\n" +
				"  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
				"  <attach><![CDATA[支付测试]]></attach>\n" +
				"  <bank_type><![CDATA[CFT]]></bank_type>\n" +
				"  <fee_type><![CDATA[CNY]]></fee_type>\n" +
				"  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
				"  <mch_id><![CDATA[10000100]]></mch_id>\n" +
				"  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
				"  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
				"  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
				"  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
				"  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
				"  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
				"  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
				"  <time_end><![CDATA[20140903131540]]></time_end>\n" +
				"  <total_fee>1</total_fee>\n" +
				"  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
				"  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
				"</xml>";
		Map<String, String> map = doXMLParse(s);
		System.out.println(map.get("appid"));
	}
}
