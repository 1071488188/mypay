package com.har.unmanned.mfront.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.har.unmanned.mfront.service.IWxUserShopService;
import org.springframework.stereotype.Service;

@Service
public class WxUserShopServiceImpl extends IWxUserShopService {
	private static final String TOKEN_REDIS_KEY = "TOKEN_REDIS_KEY:access_token";

	@Override
	public JSONObject selectGoodsList(JSONObject param) {
		return null;
	}

	@Override
	public JSONObject submitOrder(JSONObject param) {
		return null;
	}
}
