package com.har.unmanned.mfront.utils;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtil {
	public static void startPage(int pageNum, int pageSize) {
		if (pageSize != -9) {
			PageHelper.startPage(pageNum, pageSize, true);
		} else {
			log.info("不使用分页插件，传入参数：pageNum=" + pageNum + ",pageSize=" + pageSize);
		}
	}
}
