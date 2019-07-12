package cn.tswine.dynamic.datasource.annotation;


import java.lang.annotation.*;

/**
 * 数据库注解用来切换数据源
 *
 * @Author: silly
 * @Date: 2019/7/11 12:00
 * @Version 1.0
 * @Desc
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dynamic {
    String value();
}
