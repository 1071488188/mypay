package com.har.unmanned.mfront.wxapi.templateMsg;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 
* @ClassName: TemplateMsgSend
* @Description: TODO(发送模板消息)
* @author jiangjj
* @date 2017年8月18日 下午2:07:53
*
 */
@Slf4j
public class TemplateMsgSend {
	//发送消息模板请求地址
	private static final String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	public  TempMsgRetrun sendMsg(TemplateMsgPojo templateMsgPojo,String token){
		log.info("模板参数{},token{}",JSONObject.toJSON(templateMsgPojo) ,token);
		RestTemplate restTemplate = new RestTemplate();
		TempMsgRetrun tempMsgRetrun=new TempMsgRetrun();
		JSONObject params=new JSONObject();
		params=(JSONObject) JSONObject.toJSON(templateMsgPojo);
		log.info("调用微信发送消息模板接口传入参数{}",params);
		HttpEntity<String> formEntity = new HttpEntity(params);
		ResponseEntity<Map> result =restTemplate.postForEntity(TEMPLATE_SEND_URL+token, formEntity, Map.class);
	
		if(result.getStatusCode() == HttpStatus.OK){
			Object body = result.getBody();
			log.info("调用发送消息返回数据{}",body);
			Map<String, Object> resultmap = (Map<String, Object>) body;
			if("0".equals(resultmap.get("errcode"))){
				tempMsgRetrun.setIssuccess(true);
				tempMsgRetrun.setData(JSONObject.toJSONString(resultmap));
			}else{
				tempMsgRetrun.setIssuccess(false);
				tempMsgRetrun.setErrmsg(resultmap.get("errmsg")+"");
			}
		}else{
			tempMsgRetrun.setIssuccess(false);
			tempMsgRetrun.setErrmsg("请求链接失败");
		}
		log.info("执行发送模板消息返回数据{}",JSONObject.toJSON(tempMsgRetrun));
		return tempMsgRetrun;
	}

}
