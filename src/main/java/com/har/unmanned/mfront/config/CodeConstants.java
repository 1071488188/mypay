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

	/** 支付订单状态 */
	public static interface OrderStatus {

		/** 未支付 */
		public static final Integer UNPAID= 0;

		/** 已支付 */
		public static final Integer SUCCESS= 1;
	}

	/** 货架库存状态 */
	public static interface ShopStockStatus {

		/** 0下架 */
		public static final Integer DOWN = 0;

		/** 1上架 */
		public static final Integer UP = 1;
	}

	/**
	 * 提现流水类型
	 */
	public static interface WithdrawCurrentType{
		/**
		 * 佣金结算
		 */
		public static int COMMISSIONSETTLEMENT=0;
		/**
		 * 提现
		 */
		public static int WITHDRAWDEPOSIT=1;
		/**
		 * 打款失败回款
		 */
		public static int RETURNEDMONEY=2;
	}

	/**
	 * 佣金结算状态
	 */
	public static interface CommissionStatus{
		/**
		 * 待结算
		 */
		public static int FORTHE=0;
		/**
		 * 已结算
		 */
		public static int HAVEALREADYSETTLED=1;
	}

	/**
	 * 提现状态
	 */
	public static interface WithdrawalState{
		/**
		 * 待提现
		 */
		public static int FORWITHDRAWAL=0;
		/**
		 * 提现完成
		 */
		public static int WITHDRAWALTOCOMPLETE=1;
		/**
		 * 提现失败
		 */
		public static int WITHDRAWALOFFAILURE=2;
	}
}
