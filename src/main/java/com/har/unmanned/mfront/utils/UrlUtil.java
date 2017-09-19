package com.har.unmanned.mfront.utils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {
	/**
	 * 
	* @Title: dqUrl
	* @Description: TODO(获取当前完整url如果是80端口去掉80)
	* @author jiangjj
	* @return
	* @throws
	 */
	public static String dqUrl(HttpServletRequest request){
		String url="";
		String pot=request.getServerPort()==80?"":":"+request.getServerPort();
		String object=CheckUtil.isNull(request.getContextPath())?"":request.getContextPath();
		url = request.getScheme() //当前链接使用的协议     
				 +"://" + request.getServerName()//服务器地址      
				 + pot //端口号      
				 + object ;//应用名称，如果应用名称为     
				// + request.getServletPath(); //请求的相对url     

		return url;
	}
	/**
	 * 根据i值获取返回当前url的第i级
	 *
	 * @param i为0不截取
	 *
	 * @return
	 */
	public static String getSubUrl(int i) {
		HttpServletRequest request=ContextHolderUtils.getRequest();
		if(CheckUtil.isNull(request)){
			return null;
		}
		String localurl=request.getServletPath();
		String str = "";
		if (!CheckUtil.isNull(localurl)) {
			if (i == 0) {
				str = localurl;
			} else {
				//用于处理特殊情况需要i等于0的时候
				if(i==-1){
					i=0;
				}
				String[] spli = localurl.split("/");
				if (i < spli.length) {
					str = spli[i];
				}

			}

		}
		return str;
	}
}
