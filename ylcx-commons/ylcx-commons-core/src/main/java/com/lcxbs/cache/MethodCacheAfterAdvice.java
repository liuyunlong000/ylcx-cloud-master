package com.lcxbs.cache;

import net.sf.ehcache.Cache;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.List;

/**
 * create/update/delete操作时来update/remove相关cache内容
 *
 * @author fanlianwei
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean {

    private static final Logger logger = Logger.getLogger(MethodCacheAfterAdvice.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public MethodCacheAfterAdvice() {
        super();
    }

    @Override
    public void afterReturning(Object rObj, Method method, Object[] args, Object cObj) throws Throwable {
        if (method.isAnnotationPresent(ClearCache.class)) {
            ClearCache clearCache = method.getAnnotation(ClearCache.class);
            if (null != clearCache && clearCache.enable() == true) {
                String className = cObj.getClass().getName();
                List list = cache.getKeys();
                for (int i = 0; i < list.size(); i++) {
                    String cacheKey = String.valueOf(list.get(i));
                    if (cacheKey.startsWith(className)) {
                        try {
                            cache.remove(cacheKey);
                            logger.debug("remove cache key=" + cacheKey);
                        } catch (Exception ex) {
                            logger.error("", ex);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");
    }
}
