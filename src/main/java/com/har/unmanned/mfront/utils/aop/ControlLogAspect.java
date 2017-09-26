package com.har.unmanned.mfront.utils.aop;

import com.alibaba.fastjson.JSONObject;
import com.har.bigdata.log.LogHelper;
import com.har.bigdata.log.LogType;
import com.har.unmanned.mfront.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jiangjj
 * @ClassName: ControlLogAspect
 * @Description: TODO(日志打印切点)
 * @date 2017年8月16日 下午3:49:24
 */
@Aspect
@Component
@Slf4j
public class ControlLogAspect {
    @Pointcut("@annotation(com.har.unmanned.mfront.utils.aop.ControlLog)")
    public void controlLogAspect() {
    }

    /**
     * 前置通知 用于记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controlLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            Map<String, Object> map=getControllerMethodDescription(joinPoint);
            String description=map.get(SystemLogComm.ParameterKey.DESCRIPTION)+"";
            log.info("------------------"+description+"开始-------------------------------");
            String params=getParam();
            log.info(description+"传入参数:"+params);
            LogHelper.save(LogType.REQUEST, description, params);
        } catch (Exception e) {
            log.error("前置通知异常:" + e.getStackTrace());
            e.printStackTrace();
        }
    }
    /**
     *
     * @Title: After
     * @Description: TODO(返回通知)
     * @author jiangjj
     * @param joinPoint
     * @param returnValue
     * @throws
     */
    @AfterReturning(pointcut="controlLogAspect()",returning="returnValue")
    public  void After(JoinPoint joinPoint, Object returnValue) {
        try {
            Map<String, Object> map=getControllerMethodDescription(joinPoint);
            String description=map.get(SystemLogComm.ParameterKey.DESCRIPTION)+"";
            log.info("------------------"+description+"结束-------------------------------");
            log.info(description+"返回参数:"+returnValue);
            LogHelper.save(LogType.REQUEST, description, returnValue);
        }  catch (Exception e) {
            log.error("返回通知异常:"+e.getStackTrace());
            e.printStackTrace();
        }
    }
    /**
     * 获取注解中对方法的描述信息
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new TreeMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControlLog.class).description();
                    break;
                }
            }
        }
        map.put(SystemLogComm.ParameterKey.DESCRIPTION, description);
        return map;
    }

    /**
     * @return
     * @throws IOException
     * @throws
     * @Title: getParam
     * @Description: TODO(获取request参数)
     * @author jiangjj
     */
    public String getParam() throws Exception {
        JSONObject reparam = new JSONObject();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        log.info("Content-Type:====" + request.getHeader("Content-Type"));
        Enumeration enu = request.getParameterNames();
        if (enu.hasMoreElements()) {
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                reparam.put(paraName, request.getParameter(paraName));
            }
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = request.getReader();
            char[] buff = new char[1024];
            int len;

            while ((len = reader.read(buff)) != -1) {

                sb.append(buff, 0, len);
            }
            if (!CheckUtil.isNull(sb) && sb.length() > 0) {
                reparam = JSONObject.parseObject(sb.toString());
            }
        }
        return reparam.toString();
    }
}
