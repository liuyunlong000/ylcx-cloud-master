package com.lcxbs.core;

import com.lcxbs.exception.DataSourceNotFoundException;
import com.lcxbs.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用时需要定义默认数据源，名称为：defaultDataSource
 * <p>动态多数据源使用步骤：</p>
 * <blockquote><pre>
 * //创建数据源
 * DruidDataSource druidDataSource = new DruidDataSource();
 * druidDataSource.setUrl("jdbc:mysql://localhost:3306/dbname?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
 * druidDataSource.setUsername("root");
 * druidDataSource.setPassword("123456");
 * //添加数据源到数据源集合
 * DynamicDataSource.dataSourcesMap.put(dataSourceKey, druidDataSource);
 * //切换到druidDataSource
 * DynamicDataSource.setDataSource(dataSourceKey);
 *  <i>调用业务代码</i>
 * //使用完后调用移除数据源，重置为默认数据源
 * DynamicDataSource.clear();
 * </pre></blockquote>
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger log= LoggerFactory.getLogger(DynamicDataSource.class);
    /**
     * 默认数据源bean的名称
     */
    public static  final String DEFAULT_DATA_SOURCE_NAME="defaultDataSource";

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> DEFAULT_DATA_SOURCE_NAME);

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put(DEFAULT_DATA_SOURCE_NAME, SpringContextUtil.getBean(DEFAULT_DATA_SOURCE_NAME));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Object obj=DynamicDataSource.dataSourceKey.get();
        log.debug("lookup dataSourceKey="+dataSourceKey);
        return obj;
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    /**
     * 设置数据源
     * @param dataSourceKey 数据源Key
     */
    public static void setDataSource(String dataSourceKey) {
        if(!DynamicDataSource.dataSourcesMap.containsKey(dataSourceKey)){
            throw new DataSourceNotFoundException(0,"Data source["+dataSourceKey+"] not found.");
        }
        DynamicDataSource.dataSourceKey.set(dataSourceKey);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextUtil.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
        log.debug("set dataSourceKey="+dataSourceKey);
    }

    /**
     * 获取当前数据源Key
     * @return
     */
    public static String getDataSource() {
        return DynamicDataSource.dataSourceKey.get();
    }

    /**
     * 移除数据源key
     */
    public static void clear() {
        DynamicDataSource.dataSourceKey.remove();
    }
}