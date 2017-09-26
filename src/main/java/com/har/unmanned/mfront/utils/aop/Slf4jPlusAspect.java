package com.har.unmanned.mfront.utils.aop;

import org.apache.commons.logging.impl.SLF4JLogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 打印日志并接入日志系统依赖slf4j
 * Created by jiang on 2017/9/25.
 */
@Aspect
@Component
public class Slf4jPlusAspect extends SLF4JLogFactory{
    @Pointcut("@annotation(com.har.unmanned.mfront.utils.aop.Slf4jPlus)")
    public  void sf4jPlusAspect() {

    }


}
