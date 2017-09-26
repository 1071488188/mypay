package com.har.unmanned.mfront.utils.aop;
import java.lang.annotation.*;

/**  
 *自定义注解打印日志
 */    
    
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface ControlLog {
    String description()  default "";
}    
