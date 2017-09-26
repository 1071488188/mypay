package com.har.unmanned.mfront.utils.aop;
/**
 * 
* @ClassName: SystemLogComm
* @Description: TODO(aop日志相关常量)
* @author jiangjj
* @date 2017年8月16日 下午4:13:48
*
 */
public class SystemLogComm {
	/** 拦截类型 */
	public static interface InterceptType {
		/** 未定义*/
		public static final int UNDEFINED = 0;
		/** controller层拦截*/
		public static final int CONTROLLER = 1;
		/** service层拦截*/
		public static final int SERVICE = 2;
	}
	/** 切点参数key */
	public static interface ParameterKey {
		/** 描述*/
		public static final String DESCRIPTION = "description";
		/** 类型*/
		public static final String INTERCEPTTYPE = "interceptType";
	}
}
