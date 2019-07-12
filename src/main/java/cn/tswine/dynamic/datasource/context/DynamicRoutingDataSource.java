package cn.tswine.dynamic.datasource.context;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源的选择
 * @Author: silly
 * @Date: 2019/7/11 14:50
 * @Version 1.0
 * @Desc
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 根据key获取数据源的信息
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContext.getDSLabel();
    }
}
