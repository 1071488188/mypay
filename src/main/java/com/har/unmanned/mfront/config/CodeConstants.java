package com.har.unmanned.mfront.config;

/**
 * 代码常量
 *
 * @author tanzeng
 */
public class CodeConstants {

	private CodeConstants() {
	}

	/** 角色 */
	public static interface Role {

		/** 系统管理员 */
		public static final Integer ADMIN = 1;

		/** 仓库管理员 */
		public static final Integer STORE_ADMIN = 2;

		/** 网点管理员 */
		public static final Integer SHOP_ADMIN = 3;

		/** 配送员 */
		public static final Integer DISPATCHOR = 4;
	}
	/** 发送短信操作类型 */
	public static interface SmsOptType {

		/** 货架发送短信 */
		public static final Integer  UNMANNED= 40;
	}
}
