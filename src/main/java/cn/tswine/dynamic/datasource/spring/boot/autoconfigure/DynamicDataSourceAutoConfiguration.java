package cn.tswine.dynamic.datasource.spring.boot.autoconfigure;

import cn.tswine.dynamic.datasource.DynamicDataSourceFactory;
import cn.tswine.dynamic.datasource.context.DynamicDataSourceAspect;
import cn.tswine.dynamic.datasource.context.DynamicRoutingDataSource;
import cn.tswine.dynamic.datasource.properties.DataSourceProperties;
import cn.tswine.dynamic.datasource.properties.DruidStatProperties;
import cn.tswine.dynamic.datasource.properties.DynamicDataSourceProperties;
import cn.tswine.dynamic.datasource.spring.boot.autoconfigure.stat.DruidFilterConfiguration;
import cn.tswine.dynamic.datasource.spring.boot.autoconfigure.stat.DruidSpringAopConfiguration;
import cn.tswine.dynamic.datasource.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration;
import cn.tswine.dynamic.datasource.spring.boot.autoconfigure.stat.DruidWebStatFilterConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/7/11 21:37
 * @Version 1.0
 * @Desc
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({DynamicDataSourceProperties.class, DruidStatProperties.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceAspect.class,
        DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidSpringAopConfiguration.class, DruidFilterConfiguration.class})
public class DynamicDataSourceAutoConfiguration {

    @Autowired
    private DynamicDataSourceProperties dataSourceProperties;
    @Autowired
    private DruidStatProperties druidStatProperties;

    @Bean
    public DataSource routingDataSource() throws Exception {
        Map<Object, Object> targetMap = new HashMap<>();
        //默认数据源
        DataSource primaryDataSource = null;

        for (Map.Entry<String, DataSourceProperties> entry : dataSourceProperties.getDatasource().entrySet()) {
            String key = entry.getKey();
            DataSourceProperties dataSourceProperty = entry.getValue();
            dataSourceProperty.setName(key);
            DataSource dataSource = DynamicDataSourceFactory.createDataSource(dataSourceProperty, druidStatProperties);
            if (dataSourceProperty.isPrimary()) {
                primaryDataSource = dataSource;
            }
            targetMap.put(key.toUpperCase(), dataSource);
        }
        if (primaryDataSource == null) {
            primaryDataSource = (DataSource) targetMap.get("MASTER");
        }
        if (primaryDataSource == null) {
            throw new RuntimeException("not found primary datasource");
        }

        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        routingDataSource.setTargetDataSources(targetMap);
        // 设置默认使用的数据源
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        return routingDataSource;
    }

}
