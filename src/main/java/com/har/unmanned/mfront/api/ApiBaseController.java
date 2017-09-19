package com.har.unmanned.mfront.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 基类
 * @author tanzeng
 */
@Slf4j
public class ApiBaseController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		// 对输入字符串参数转义
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true, true));
	}
	
}
