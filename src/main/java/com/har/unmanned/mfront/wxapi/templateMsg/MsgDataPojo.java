package com.har.unmanned.mfront.wxapi.templateMsg;

public class MsgDataPojo{
	private String value;
	private String color;
	public MsgDataPojo(String value,String color){
		this.color = "#"+color;
		this.value = value;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
