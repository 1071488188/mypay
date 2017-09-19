package com.har.unmanned.mfront.wxapi.fixed;


import com.har.unmanned.mfront.service.impl.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/1 14:57.
 */
@Slf4j
@Service
public class WxTokenService {
	@Value("${wx.token.tokenUrl}")
	private String tokenUrl;
	@Autowired
	private RedisServiceImpl service;

	public void getToken() {
		RestTemplate restTemplate = new RestTemplate();
		log.info("执行定时任务获取token传入参数:" + tokenUrl);
		ResponseEntity<Map> result = restTemplate.postForEntity(tokenUrl, null, Map.class);
		if (result.getStatusCode() == HttpStatus.OK) {
			Object body = result.getBody();
			Map<String, Object> token = (Map<String, Object>) body;
			// log.info("执行定时任务获取token微信返回参数:"+JSONObject.toJSONString(token));
			if (token.get("errcode") == null) {
				String access_token = token.get("access_token") + "";
				log.info("微信传回token信息{}",access_token);
				service.put("access_token", access_token, 5400);
			} else {
				log.info("获取token失败{}",body);
			}
		} else {
			log.info("执行定时任务获取token传入参数{}:" + result);
		}
	}

}
