package com.har.unmanned.mfront.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: ContextHolderUtils
 * @Description: TODO(上下文工具类)
 * @author jjj
 * @date
 *
 */
public class ContextHolderUtils {
	  private static final String[] HEADERS_TO_TRY = { 
	        "X-Forwarded-For",
	        "Proxy-Client-IP",
	        "WL-Proxy-Client-IP",
	        "HTTP_X_FORWARDED_FOR",
	        "HTTP_X_FORWARDED",
	        "HTTP_X_CLUSTER_CLIENT_IP",
	        "HTTP_CLIENT_IP",
	        "HTTP_FORWARDED_FOR",
	        "HTTP_FORWARDED",
	        "HTTP_VIA",
	        "REMOTE_ADDR",
	        "X-Real-IP"};
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;

	}

	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;

	}
	/**
	 * 判断是微信还是支付宝
	 * 
	 * @return
	 */
	public static String getClaint() {
		String flag = "";
		String header = getRequest().getHeader("user-agent");
		if (!CheckUtil.isNull(header)) {
			if (header.indexOf("AlipayClient") > -1 || header.indexOf("AliApp(AP") > -1) {
				flag = "alipay";
			} else if (header.indexOf("MicroMessenger") > -1) {
				flag = "wx";
			}
		}
		return flag;
	}

	/**
	 * 获取ip地址
	 * @return
	 */
	public static String getIp() {
		HttpServletRequest request =  getRequest();
		return request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr() : request
				.getHeader("x-forwarded-for");
	}
}
