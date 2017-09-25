package com.har.unmanned.mfront.utils.aop;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jiang on 2017/9/25.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Slf4jPlus{
}
