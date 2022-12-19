package com.lcxbs.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 清除缓存声明
 *
 * @author fanlianwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ClearCache {

    /**
     * 是否启用清除缓存
     *
     * @return
     */
    public boolean enable() default true;
}
