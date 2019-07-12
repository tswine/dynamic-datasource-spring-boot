package cn.tswine.dynamic.datasource.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 动态数据源属性
 *
 * @Author: silly
 * @Date: 2019/7/11 19:22
 * @Version 1.0
 * @Desc
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDataSourceProperties {

    /**
     * 每个数据源
     */
    private Map<String, DataSourceProperties> datasource = new LinkedHashMap<>();


}
