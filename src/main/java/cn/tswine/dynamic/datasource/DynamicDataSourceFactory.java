package cn.tswine.dynamic.datasource;

import cn.tswine.dynamic.datasource.properties.DataSourceProperties;
import cn.tswine.dynamic.datasource.properties.DruidStatProperties;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 动态数据源工厂，指定数据源为:druid
 *
 * @Author: silly
 * @Date: 2019/7/11 21:52
 * @Version 1.0
 * @Desc
 */
@Slf4j
public class DynamicDataSourceFactory {

    /**
     * 创建DataSource
     *
     * @param dataSourceProperty
     * @param druidStatProperties
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static DataSource createDataSource(DataSourceProperties dataSourceProperty, DruidStatProperties druidStatProperties) throws Exception {
        getCommonProperties(dataSourceProperty, druidStatProperties);
        DruidDataSource dataSource = new DruidDataSource();
        config(dataSource, dataSourceProperty);
        return dataSource;
    }

    /**
     * 配置DataSource参数
     *
     * @param dataSource
     * @param property
     * @throws SQLException
     */
    @SuppressWarnings({"deprecation", "rawtypes"})
    public static void config(DruidDataSource dataSource, DataSourceProperties property) throws SQLException {
        if (property.getName() != null) {
            dataSource.setName(property.getName());
        }
        if (property.getDriverClassName() != null) {
            dataSource.setDriverClassName(property.getDriverClassName());
        }
        if (property.getUrl() != null) {
            dataSource.setUrl(property.getUrl());
        }
        if (property.getUsername() != null) {
            dataSource.setUsername(property.getUsername());
        }
        if (property.getPassword() != null) {
            dataSource.setPassword(property.getPassword());
        }
        if (property.getFilters() != null) {
            dataSource.setFilters(property.getFilters());
        }
        if (property.getTestOnBorrow() != null) {
            dataSource.setTestOnBorrow(property.getTestOnBorrow());
        }
        if (property.getTestWhileIdle() != null) {
            dataSource.setTestWhileIdle(property.getTestWhileIdle());
        }
        if (property.getTestOnReturn() != null) {
            dataSource.setTestOnReturn(property.getTestOnReturn());
        }
        if (property.getInitialSize() != null) {
            dataSource.setInitialSize(property.getInitialSize());
        }
        if (property.getMinIdle() != null) {
            dataSource.setMinIdle(property.getMinIdle());
        }
        if (property.getMaxActive() != null) {
            dataSource.setMaxActive(property.getMaxActive());
        }
        if (property.getMaxWait() != null) {
            dataSource.setMaxWait(property.getMaxWait());
        }
        if (property.getQueryTimeout() != null) {
            dataSource.setQueryTimeout(property.getQueryTimeout());
        }
        if (property.getTransactionQueryTimeout() != null) {
            dataSource.setTransactionQueryTimeout(property.getTransactionQueryTimeout());
        }
        if (property.getLoginTimeout() != null) {
            dataSource.setLoginTimeout(property.getLoginTimeout());
        }
        if (property.getAsyncInit() != null) {
            dataSource.setAsyncInit(property.getAsyncInit());
        }
        if (property.getPoolPreparedStatements() != null) {
            dataSource.setPoolPreparedStatements(property.getPoolPreparedStatements());
        }
        if (property.getMaxPoolPreparedStatementPerConnectionSize() != null) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(property.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (property.getConnectionProperties() != null) {
            dataSource.setConnectionProperties(property.getConnectionProperties());
        }
        if (property.getMaxIdle() != null) {
            dataSource.setMaxIdle(property.getMaxIdle());
        }
        if (property.getTimeBetweenEvictionRunsMillis() != null) {
            dataSource.setTimeBetweenEvictionRunsMillis(property.getTimeBetweenEvictionRunsMillis());
        }
        if (property.getMinEvictableIdleTimeMillis() != null) {
            dataSource.setMinEvictableIdleTimeMillis(property.getMinEvictableIdleTimeMillis());
        }
        if (property.getValidationQuery() != null) {
            dataSource.setValidationQuery(property.getValidationQuery());
        }
    }

    /**
     * 获取公共参数
     *
     * @param dataSourceProperties
     * @param druidStatProperties
     */
    private static void getCommonProperties(DataSourceProperties dataSourceProperties, DruidStatProperties druidStatProperties) {
        if (dataSourceProperties.getFilters() == null) {
            dataSourceProperties.setFilters(druidStatProperties.getFilters());
        }
        if (dataSourceProperties.getTestOnBorrow() == null) {
            dataSourceProperties.setTestOnBorrow(druidStatProperties.getTestOnBorrow());
        }
        if (dataSourceProperties.getTestWhileIdle() == null) {
            dataSourceProperties.setTestWhileIdle(druidStatProperties.getTestWhileIdle());
        }
        if (dataSourceProperties.getTestOnReturn() == null) {
            dataSourceProperties.setTestOnReturn(druidStatProperties.getTestOnReturn());
        }
        if (dataSourceProperties.getInitialSize() == null) {
            dataSourceProperties.setInitialSize(druidStatProperties.getInitialSize());
        }
        if (dataSourceProperties.getMinIdle() == null) {
            dataSourceProperties.setMinIdle(druidStatProperties.getMinIdle());
        }
        if (dataSourceProperties.getMaxActive() == null) {
            dataSourceProperties.setMaxActive(druidStatProperties.getMaxActive());
        }
        if (dataSourceProperties.getMaxWait() == null) {
            dataSourceProperties.setMaxWait(druidStatProperties.getMaxWait());
        }
        if (dataSourceProperties.getQueryTimeout() == null) {
            dataSourceProperties.setQueryTimeout(druidStatProperties.getQueryTimeout());
        }
        if (dataSourceProperties.getTransactionQueryTimeout() == null) {
            dataSourceProperties.setTransactionQueryTimeout(druidStatProperties.getTransactionQueryTimeout());
        }
        if (dataSourceProperties.getLoginTimeout() == null) {
            dataSourceProperties.setLoginTimeout(druidStatProperties.getLoginTimeout());
        }
        if (dataSourceProperties.getAsyncInit() == null) {
            dataSourceProperties.setAsyncInit(druidStatProperties.getAsyncInit());
        }
        if (dataSourceProperties.getPoolPreparedStatements() == null) {
            dataSourceProperties.setPoolPreparedStatements(druidStatProperties.getPoolPreparedStatements());
        }
        if (dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize() == null) {
            dataSourceProperties.setMaxPoolPreparedStatementPerConnectionSize(druidStatProperties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (dataSourceProperties.getConnectionProperties() == null) {
            dataSourceProperties.setConnectionProperties(druidStatProperties.getConnectionProperties());
        }
        if (dataSourceProperties.getMaxIdle() == null) {
            dataSourceProperties.setMaxIdle(druidStatProperties.getMaxIdle());
        }
        if (dataSourceProperties.getTimeBetweenEvictionRunsMillis() == null) {
            dataSourceProperties.setTimeBetweenEvictionRunsMillis(druidStatProperties.getTimeBetweenEvictionRunsMillis());
        }
        if (dataSourceProperties.getMinEvictableIdleTimeMillis() == null) {
            dataSourceProperties.setMinEvictableIdleTimeMillis(druidStatProperties.getMinEvictableIdleTimeMillis());
        }
        if (dataSourceProperties.getValidationQuery() == null) {
            dataSourceProperties.setValidationQuery(druidStatProperties.getValidationQuery());
        }
    }

}

