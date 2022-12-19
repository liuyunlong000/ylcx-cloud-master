package com.lcxbs.cache;

import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * get/find等缓存方法拦截器
 *
 * @author fanlianwei
 */
public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {

    private static final Logger log = Logger.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * implement InitializingBean，检查cache是否为空
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");
        log.info(cache + " A cache is required. Use setCache(Cache) to provide one.");
    }

    /**
     * 拦截Service/DAO的方法，并查找该结果是否存在，如果存在就返回cache中的值， 否则，返回数据库查询结果，并将查询结果放入cache
     *
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (invocation.getMethod().isAnnotationPresent(InsertCache.class)) {
            InsertCache cacheNew = invocation.getMethod().getAnnotation(InsertCache.class);
            if (null == cacheNew || cacheNew.enable() == false) {
                return invocation.proceed(); // 调用实际的方法;
            }
            String targetName = invocation.getThis().getClass().getName();
            String methodName = invocation.getMethod().getName();
            Object[] arguments = invocation.getArguments();
            Object result;
            String cacheKey = getCacheKey(targetName, methodName, arguments);
            Element element = null;
            synchronized (this) {
                element = cache.get(cacheKey);
                if (element == null) {
                    result = invocation.proceed(); // 调用实际的方法
                    if (null != result) {
                        element = new Element(cacheKey, (Serializable) result);
                        cache.put(element);
                        log.debug("key=" + cacheKey + " added to cache=" + cache.getName());
                    } else {
                        return null;
                    }
                } else {
                    log.debug("key=" + cacheKey + " Retrieved from the cache= " + cache.getName());
                }
            }
            return element.getObjectValue();
        } else {
            return invocation.proceed(); // 调用实际的方法;
        }
    }

    /**
     * <b>function:</b> 返回具体的方法全路径名称 参数; cache key包括
     * 包名+类名+方法名，如com.suncreate.cache.service.UserServiceImpl.getModelById
     *
     * @param targetName 全路径
     * @param methodName 方法名称
     * @param arguments 参数
     * @return 完整方法名称
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }
}
