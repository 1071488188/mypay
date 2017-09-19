package com.har.unmanned.mfront.wxapi.templateMsg;

import java.util.Map;

/**
 * 微信消息模板JSON数据实体
 * 
 *
 */
public class TemplateMsgPojo {
	private String touser;
	private String template_id;
	private String url;
	private Map<String,MsgDataPojo> data;
	public Map<String, MsgDataPojo> getData() {
		return data;
	}
	public void setData(Map<String, MsgDataPojo> data) {
		this.data = data;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}