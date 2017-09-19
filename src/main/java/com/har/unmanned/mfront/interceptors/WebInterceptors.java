package com.har.unmanned.mfront.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器注册
 */
@Slf4j
@Configuration
@Component
public class WebInterceptors extends WebMvcConfigurerAdapter {

    @Autowired
    JwtCheckInterceptor jwtCheckInterceptor;

    @Value("${wx.auth.interceptor.excluded}")
    public String interceptorExcluded;

    public String[] excluded;

    /**
     * 拦截器配置
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("=====注册拦截器开始-START=====");
        // 需要排除拦截的地址初始化
        interceptorInit();
        // 注册JWT检验拦截
        registry.addInterceptor(jwtCheckInterceptor).addPathPatterns("/**").excludePathPatterns(interceptorExcluded);;
        log.info("=====注册拦截器结束-OVER=====");
    }

    public void interceptorInit() {
        log.info("=====添加排除拦截地址开始=====");
        log.info("=====需要排除拦截地址：[" + interceptorExcluded + "]");
        excluded = interceptorExcluded.split(",");
        log.info("=====添加排除拦截地址完成=====");
    }

}
