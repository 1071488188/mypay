package com.har.unmanned.mfront.exception;


import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.exception.CommonException;
import com.har.bigdata.log.LogHelper;
import com.har.unmanned.mfront.config.Constants;
import com.har.unmanned.mfront.config.ErrorCode;
import com.har.unmanned.mfront.utils.CheckUtil;
import com.har.unmanned.mfront.utils.ContextHolderUtils;
import com.har.unmanned.mfront.utils.RespMessage;
import com.har.unmanned.mfront.utils.WxAuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 对外API异常处理器
 * 
 * @author tanzeng
 * 
 */
@Slf4j
@Component
public class ApiExceptionHandler implements HandlerExceptionResolver {
	@Autowired
	WxAuthUtil wxAuthUtil;
	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;UTF-8");

			RespMessage respMessage = new RespMessage();

			if (ex instanceof ApiBizException) {
				String errcode=((ApiBizException) ex).getErrCode();
				respMessage.setRespCode(errcode);
				respMessage.setRespDesc(ex.getMessage());
				HttpServletRequest httpServletRequest=ContextHolderUtils.getRequest();
				//当为未授权的时候跳转拼装用户授权地址
				if(!CheckUtil.isNull(httpServletRequest)&&ErrorCode.E00000006.CODE.equals(errcode)){
					String currenturl=httpServletRequest.getHeader(Constants.constantHead.CURRENTURL);
					JSONObject jsonObject=new JSONObject();
					String reduurl= wxAuthUtil.getAuthUrl(Constants.SCOPR_base, URLEncoder.encode(currenturl));
					jsonObject.put("redirectUrl", reduurl);
					respMessage.setData(jsonObject);
				}
				LogHelper.saveCommonException((CommonException) ex);
			} else {
				LogHelper.saveException(Constants.Topic, ex);
				respMessage.setRespCode(ErrorCode.E00000001.CODE);
				respMessage.setRespDesc(ErrorCode.E00000001.MSG);
			}

			writer = response.getWriter();
			writer.write(respMessage.getRespMessage().toJSONString());
			writer.flush();
		} catch (Exception e) {
			LogHelper.saveException(Constants.Topic, ex);
			log.error("" + e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		return null;
	}
}
