package com.har.unmanned.mfront.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtil {
	public static Page startPage(int pageNum, int pageSize) {
		if (pageSize != -9) {
			Page page=PageHelper.startPage(pageNum, pageSize, true);
			return page;
		} else {
			log.info("不使用分页插件，传入参数：pageNum=" + pageNum + ",pageSize=" + pageSize);
			return null;
		}
	}
}
