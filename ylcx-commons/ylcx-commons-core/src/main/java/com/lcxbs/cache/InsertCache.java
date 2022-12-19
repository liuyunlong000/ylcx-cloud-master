package com.lcxbs.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加入缓存声明
 * 注解适用地方(类和方法)  
 * @author fanlianwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface InsertCache {

    /**
     * 是否启用加入缓存
     *
     * @return
     */
    public boolean enable() default true;
}
