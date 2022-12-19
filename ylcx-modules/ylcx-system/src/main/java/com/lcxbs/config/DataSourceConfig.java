package com.lcxbs.config;

import com.lcxbs.core.DynamicDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Resource
    private DataSource defaultDataSource;

    /**
     * 定义默认数据源,名称为:
     * @see DynamicDataSource#DEFAULT_DATA_SOURCE_NAME
     * @return
     */
    /**
     已经在类 @see com.lcxbs.auth.MyAuthServerConfig 定义，不需要重新定义
     @Bean(name = DynamicDataSource.DEFAULT_DATA_SOURCE_NAME)
     @ConfigurationProperties(prefix = "spring.datasource")
     public DataSource defaultDataSource() {
     return DataSourceBuilder.create().build();
     }
     */

    @Bean
    @Primary
    @DependsOn({"springContextUtil", DynamicDataSource.DEFAULT_DATA_SOURCE_NAME})
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        return dynamicDataSource;
    }

    /**
     * mybatis的xml中databaseId区分数据库配置
     * @param
     * @return
     * @author MEI SHEN  BO
     * @date 2021/11/5
     **/
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("MySQL", "mysql");
        p.setProperty("PostgreSQL", "postgresql");
        p.setProperty("DB2", "db2");
        p.setProperty("SQL Server", "sqlserver");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}