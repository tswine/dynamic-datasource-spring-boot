package cn.tswine.dynamic.datasource.context;

import cn.tswine.dynamic.datasource.annotation.Dynamic;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 李永AOP动态切换数据源
 *
 * @Author: silly
 * @Date: 2019/7/11 14:59
 * @Version 1.0
 * @Desc
 */
@Aspect
@Component
//保证该AOP在@Transactional之前执行，值越小越先执行
@Order(-1)
public class DynamicDataSourceAspect {

    /**
     * 切点
     */
    @Pointcut("@within(cn.tswine.dynamic.datasource.annotation.Dynamic) || @annotation(cn.tswine.dynamic.datasource.annotation.Dynamic)")
    public void pointcut() {
    }

    /**
     * 方法执行前先执行
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //获取方法上的注解
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解值
        Dynamic annotation = method.getAnnotation(Dynamic.class);
        String dsLabel;
        if (Objects.isNull(annotation)) {
            //方法没有注解，则获取类上的注解
            annotation = joinPoint.getTarget().getClass().getAnnotation(Dynamic.class);
            if (Objects.isNull(annotation)) {
                return;
            }
        }
        //获取注解值
        dsLabel = annotation.value();
        //切换数据源
        DynamicDataSourceContext.setDSLabel(dsLabel.toUpperCase());
    }

    /**
     * 方法执行完后
     */
    @After("pointcut()")
    public void doAfter() {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DynamicDataSourceContext.clear();
    }
}
