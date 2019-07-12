package cn.tswine.dynamic.datasource.properties;

import lombok.Data;

/**
 * 动态数据源属性
 *
 * @Author: silly
 * @Date: 2019/7/11 19:45
 * @Version 1.0
 * @Desc
 */
@Data
public class DataSourceProperties  extends DynamicCommonProperties{
    /**
     * 是否默认库
     */
    private boolean primary = false;
    /**
     * 名称
     */
    private String name;
    /**
     * 驱动类名
     */
    private String driverClassName;
    /**
     * 连接地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
