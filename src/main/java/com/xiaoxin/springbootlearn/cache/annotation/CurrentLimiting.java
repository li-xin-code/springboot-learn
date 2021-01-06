package com.xiaoxin.springbootlearn.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流
 * @author lx
 * @date 2021/1/5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CurrentLimiting {
    int seconds() default 3;
    int maxCount() default 10;
}
