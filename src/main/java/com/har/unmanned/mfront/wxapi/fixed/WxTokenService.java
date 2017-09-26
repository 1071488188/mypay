package com.har.unmanned.mfront.wxapi.fixed;


import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.exception.ApiBizException;
import com.har.unmanned.mfront.service.impl.RedisServiceImpl;
import com.har.unmanned.mfront.utils.CheckUtil;
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

	/**
	 * 定时任务调用微信获取token
	 */
	public void postToken() {
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
				service.put(Constants.WX_TOKEN, access_token, 5400);
			} else {
				log.info("获取token失败{}",body);
			}
		} else {
			log.info("执行定时任务获取token传入参数{}:" + result);
		}
	}

	/**
	 * 获取redis里面微信token,如果未获取到则调用接口继续获取
	 * @return
	 */
	public String getToken() throws ApiBizException {
		String retoken="";
		Object object=service.get(Constants.WX_TOKEN);
		if(!CheckUtil.isNull(object)){
			retoken=object+"";
			log.info("redis保存的token{}",retoken);
		}else{
			retoken = synchronizationGetToken();
		}
		if(CheckUtil.isNull(retoken)){
			log.info("未获取到微信token");
			throw new ApiBizException(ErrorCode.E00000001.CODE,"系统参数异常",null);
		}
		return retoken;
	}

	private synchronized String synchronizationGetToken() throws ApiBizException {
		String retoken;RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> result = restTemplate.postForEntity(tokenUrl, null, Map.class);
		if (result.getStatusCode() != HttpStatus.OK) {
            log.info("服务器网络异常未获取到系统参数");
            throw new ApiBizException(ErrorCode.E00000001.CODE,"服务器网络异常未获取到系统参数",null);
        }
		Object body = result.getBody();
		Map<String, Object> token = (Map<String, Object>) body;
		if ( !CheckUtil.isNull(token.get("errcode"))) {
            log.info("获取token失败{}",body);
            throw new ApiBizException(ErrorCode.E00000001.CODE,"获取系统参数失败",null);
        }
		String access_token = token.get("access_token") + "";
		log.info("微信传回token信息{}",access_token);
		service.put(Constants.WX_TOKEN, access_token, 5400);
		retoken=access_token;
		return retoken;
	}

}
