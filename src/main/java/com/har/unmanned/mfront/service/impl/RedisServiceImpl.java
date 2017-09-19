package com.har.unmanned.mfront.service.impl;

import com.har.unmanned.mfront.service.IRedisService;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl extends IRedisService<String> {
	private static final String TOKEN_REDIS_KEY = "TOKEN_REDIS_KEY:access_token";
	@Override
	protected String getRedisKey() {
		// TODO Auto-generated method stub
		return this.TOKEN_REDIS_KEY;
	}

}
