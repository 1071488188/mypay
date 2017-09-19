package com.har.unmanned.mfront.config;

public class ErrorCode {

	public interface E00000000 {
		
		String CODE = "00000000";
		
		String MSG = "响应成功";
	}
	
	public interface E00000001 {

		String CODE = "00000001";

		String MSG = "服务器开小差了";
	}

	public interface E00000002 {

		String CODE = "00000002";

		String MSG = "响应失败";
	}
	
	public interface E00000003 {
		
		String CODE = "00000003";
		
		String MSG = "用户未登录";
	}
	
	public interface E00000004 {
		
		String CODE = "00000004";
		
		String MSG = "登录超时";
	}
	
	public interface E00000005 {
		
		String CODE = "00000005";
		
		String MSG = "数据解析异常";
	}
	
	public interface E00000006 {
		
		String CODE = "00000006";
		
		String MSG = "用户未授权";
	}

	public interface E00000007 {

		String CODE = "00000007";

		String MSG = "获取授权Token失败";
	}

	public interface E00000008 {

		String CODE = "00000008";

		String MSG = "获取商户数据失败";
	}

	public interface E00000009 {

		String CODE = "00000009";

		String MSG = "用户未绑定手机号";
	}

	public interface E00000010 {

		String CODE = "00000010";

		String MSG = "微信授权异常";
	}

	public interface E00000011 {

		String CODE = "00000011";

		String MSG = "微信回调重复请求";
	}

	public interface E00000012 {

		String CODE = "00000012";

		String MSG = "请求数据不全";
	}

	public interface E00000013 {

		String CODE = "00000013";

		String MSG = "每个用户只能有三条订阅";
	}

	public interface E00000014 {

		String CODE = "00000014";

		String MSG = "存在重复的订阅条件";
	}
}
