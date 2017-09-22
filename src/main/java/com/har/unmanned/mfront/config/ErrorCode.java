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

		String MSG = "配送中心查询失败";
	}

	public interface E00000015 {

		String CODE = "00000015";

		String MSG = "权限不足";
	}

	public interface E00000016 {

		String CODE = "00000016";

		String MSG = "微信统一下单失败";
	}

	public interface E00000017 {

		String CODE = "00000017";

		String MSG = "微信订单查询失败";
	}

	public interface E00000018 {

		String CODE = "00000018";

		String MSG = "微信订单回调失败";
	}

	public interface E00000019 {

		String CODE = "00000019";

		String MSG = "微信签名验证失败";
	}

	public interface E00000020 {

		String CODE = "00000020";

		String MSG = "微信订单支付失败";
	}

	public interface E00000021 {

		String CODE = "00000021";

		String MSG = "商品库存异常";
	}

	public interface E00000022 {

		String CODE = "00000022";

		String MSG = "商品库存扣减异常";
	}

	public interface E00000023 {

		String CODE = "00000023";

		String MSG = "配送单状态更新失败";
	}

	public interface E00000024 {

		String CODE = "00000024";

		String MSG = "提交的商品与配送单商品不匹配";
	}

	public interface E00000025 {

		String CODE = "00000025";

		String MSG = "补货失败";
	}
}
