# dynamic-datasource-spring-boot

<p>
    <a href="http://www.apache.org/licenses/LICENSE-2.0.html" target="_blank">
        <img src="http://img.shields.io/:license-apache-brightgreen.svg" >
    </a>
    <a>
        <img src="https://img.shields.io/badge/release-v1.0.0-blue.svg">
    </a>
    <a>
        <img src="https://img.shields.io/badge/JDK-1.8%2B-blue.svg">
    </a>
    <a href="https://spring.io/projects/spring-boot/">
        <img src="https://img.shields.io/badge/SpringBoot-2.0%2B-blue.svg">
    </a>
    <a href="https://github.com/alibaba/druid">
        <img src="https://img.shields.io/badge/druid-1.1.18-blue.svg">
    </a>
</p>

#### [Github](https://github.com/tswine/dynamic-datasource-spring-boot)

# 介绍
dynamic-datasource-spring-boot基于spring boot采用注解的方式自动切换数据源，并且集成druid监控配置

# 安装
- git clone https://github.com/tswine/dynamic-datasource-spring-boot.git
- cd dynamic-datasource-spring-boot && mvn install
- hava fun

# 使用
## 1. maven配置
```
  <dependency>
    <groupId>cn.tswine.springboot</groupId>
    <artifactId>dynamic-datasource-spring-boot</artifactId>
    <version>${version}</version>
  </dependency>
```

## 2.参数配置
| 节点名称       | 父节点    |  描述  |
| --------      | :-----:    | :----: |
| datasource    | spring    |   数据库配置根节点：spring.datasource|
| -druid             | datasource      |   druid相关配置，配置监控及公共参数    |
| --web-stat-filter             | druid      |   druid监控相关配置    |
| --stat-view-servlet             | druid      |   druid监控相关配置    |
| --公共参数配置             | druid      |   druid数据源的其他相关配置    |
| -dynamic             | datasource      |   多数据源配置    |
| --datasource             | dynamic      |   标记配置数据源参数    |
| ---数据源别名 | datasource | 自定义数据源名称，@Dynamic注解的value值相对应，可配置多个 |
| ----primary | 数据源名 | 是否为主库，默认值：false  |
| ----driverClassName | 数据源名 | 驱动类名 |
| ----url | 数据源名 | 数据库连接url |
| ----username | 数据源名 | 登录名 |
| ----password | 数据源名 | 密码 |
| ----公共参数配置 | 数据源名 | druid数据源的其他相关配置，单个数据源的配置会优先覆盖druid全局中的公共参数配置，如未配置则使用druid中的配置 |

- druid监控配置可参考druid-spring-boot-starter开源项目的参数配置：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter 
- druid数据源的其他相关配置可参考：https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE
- yml 配置样例
    ```
    spring:
        datasource:
            druid:
                web-stat-filter:
                    enabled: true
                stat-view-servlet:
                    enabled: true
                    url-pattern: /druid/*
                    login-username: admin
                    login-password: 123456
                # 连接池的配置信息
                # 初始化大小，最小，最大
                initial-size: 5
                min-idle: 5
                maxActive: 20
                 # 配置获取连接等待超时的时间
                maxWait: 60000
                # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                timeBetweenEvictionRunsMillis: 60000
                # 配置一个连接在池中最小生存的时间，单位是毫秒
                 minEvictableIdleTimeMillis: 300000
                validationQuery: SELECT 1 FROM DUAL
                testWhileIdle: true
                testOnBorrow: false
                testOnReturn: false
                # 打开PSCache，并且指定每个连接上PSCache的大小
                poolPreparedStatements: true
                maxPoolPreparedStatementPerConnectionSize: 20
                # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
                filters: stat,wall,slf4j
                # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
                connectionProperties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
            dynamic:
               datasource:
                master:
                    primary: true
                    driverClassName: com.mysql.jdbc.Driver
                    url: jdbc:mysql://192.168.47.100:3306/dynamic_datasource?characterEncoding=UTF-8&useUnicode=true&useSSL=false
                    username: system
                    password: 123456
                log:
                    driverClassName: com.mysql.jdbc.Driver
                    url: jdbc:mysql://192.168.47.100:3306/dynamic_datasource_log?characterEncoding=UTF-8&useUnicode=true&useSSL=false
                    username: system
                    password: 123456
    ```

## 3.注解使用
- @Dynamic(value="配置的数据源名")
- 注解需在service的实现类标记
- 注解在类上则代表该类的所有参数都使用注解的数据源
- 注解在方法上则代表该方法使用注解的数据源，注解在方法的优先级大于类的注解
- 未使用任何的注解，则采用默认的数据源

&emsp;如何使用dynamic-datasource-spring-boot可参考示例代码：https://github.com/tswine/sample/tree/master/spring-boot-sample/sample-dynamic-datasource-spring-boot


