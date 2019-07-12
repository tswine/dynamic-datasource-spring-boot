package cn.tswine.dynamic.datasource.properties;

import lombok.Data;

/**
 * 公共参数属性
 *
 * @Author: silly
 * @Date: 2019/7/12 10:25
 * @Version 1.0
 * @Desc
 */
@Data
public class DynamicCommonProperties {
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    protected String filters;
    /**
     * 是否获取连接时检测
     */
    protected Boolean testOnBorrow;
    /**
     * 是否空闲时检测
     */
    protected Boolean testWhileIdle;
    /**
     * 是否连接放回连接池时检测
     */
    protected Boolean testOnReturn;
    /**
     * 初始化连接大小
     */
    protected Integer initialSize;
    /**
     * 最小空闲连接数
     */
    protected Integer minIdle;
    /**
     * 最大连接数
     */
    protected Integer maxActive;
    /**
     * 获取连接等待超时的时间
     */
    protected Integer maxWait;
    /**
     * 查询超时时间
     */
    protected Integer queryTimeout;
    /**
     * 事务查询超时时间
     */
    protected Integer transactionQueryTimeout;
    /**
     * 登录超时时间
     */
    protected Integer loginTimeout;

    protected Boolean asyncInit;
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    protected Boolean poolPreparedStatements;
    protected Integer maxPoolPreparedStatementPerConnectionSize;

    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    protected String connectionProperties;

    /**
     * 连接池最大数量
     */
    protected Integer maxIdle;
    
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    protected Long timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    protected Long minEvictableIdleTimeMillis;
    /**
     * 验证数据库的查询语句
     */
    protected String validationQuery;
}
