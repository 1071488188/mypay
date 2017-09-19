package com.har.unmanned.mfront.utils;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json与xml转换工具类
 * @author yb
 */
@SuppressWarnings("rawtypes")
public class Xml2JsonUtil {
	/*private static String xml = "<?xml version='1.0' encoding='UTF-8'?>"
			+ "<response>"
			+ "<code_query_list><brandId>0020</brandId><cardId>9999990024000117739</cardId><crtDt>20150605</crtDt><phoneNum>18501770977</phoneNum><preSeriesCode>sys</preSeriesCode><seriesCode>8740003435733678</seriesCode><stat>2</stat><txnAt>10000</txnAt></code_query_list>"
			+ "<code_query_list><brandId>0020</brandId><cardId>9999990024000117739</cardId><crtDt>20150605</crtDt><phoneNum>18501770977</phoneNum><preSeriesCode>8740003435733678</preSeriesCode><seriesCode>8740021238421512</seriesCode><stat>2</stat><txnAt>9999</txnAt></code_query_list>"
			+ "<tot_count>15</tot_count>"
			+ "</response>";
	private static String xml1 = "<?xml version='1.0' encoding='UTF-8'?><code_response><order_id>1000018484</order_id><phone_num>18501770977</phone_num><res_code>0</res_code><series_code>8740051853072962</series_code></code_response>";
	
	private static String xml2 = "<?xml version='1.0' encoding='GB2312'?>"
			+ "<orderinfo>"
			+ "<err_msg />"
			+ "<retcode>1</retcode >"
			+ "<orderid>S1410140541452</orderid>"
			+ "<cardid>1221601</cardid>"
			+ "<cardnum>1</cardnum>"
			+ "<ordercash>49.5</ordercash>"
			+ "<cardname>江苏移动50元70M省内当日生效当月有效2G流量包</cardname>"
			+ "<sporder_id>200912180001</sporder_id>"
			+ "<game_userid>13813834333</game_userid>"
			+ "<game_state>0</game_state>"
			+ "</orderinfo>";*/
	/**
	 * xml转jsonObject(获取所有节点的键值对)
	 * @param xml
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject xml2JsonObject(String xml) throws Exception{
		Document doc = (Document)DocumentHelper.parseText(xml);  
        Element root = doc.getRootElement(); 
        JSONObject xmlJson = new JSONObject();
		return getElement(root, xmlJson);
	}
	
	public static JSONObject getElement(Element root, JSONObject xmlJson) throws Exception{
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			if(e.getText() == null || "".equals(e.getText())){
				getElement(e, xmlJson);
			}else{
				xmlJson.put(e.getName(), e.getText());
			}
		}
		return xmlJson;
	}
	
	/**
	 * xml转jsonArray(暂支持一层list)
	 * @param xml
	 * @param xmlJson
	 * @return
	 * @throws Exception
	 */
	public static JSONObject xml2JsonArray(String xml,JSONObject xmlJson) throws Exception{
		Document doc = (Document)DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		Iterator it = root.elementIterator();
		JSONArray array = new JSONArray();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			System.out.println(e.getName());
			JSONObject xml2Json = new JSONObject();
			if(xmlJson.containsKey(e.getName())){
				array.add(getElement(e, xml2Json));
				xmlJson.put(e.getName(), array);
			}else{
				array = new JSONArray();
				if(e.getText() == null || "".equals(e.getText())){
					array.add(getElement(e, xml2Json));
					xmlJson.put(e.getName(), array);
				}else{
					xmlJson.put(e.getName(), e.getText());
				}
			}
		}
		return xmlJson;
	}
}
