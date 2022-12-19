package com.lcxbs.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * 获取spring容器，以访问容器中定义的其他bean
 *
 */
@Component
public final class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringContextUtil.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringContextUtil.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringContextUtil.applicationContext.getEnvironment().getProperty(key);
    }
}
