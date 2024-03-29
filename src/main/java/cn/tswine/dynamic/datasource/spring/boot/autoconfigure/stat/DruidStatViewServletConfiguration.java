package cn.tswine.dynamic.datasource.spring.boot.autoconfigure.stat;

import cn.tswine.dynamic.datasource.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 配置Druid的内置监控页面
 *
 * @Author: silly
 * @Date: 2019/7/12 9:40
 * @Version 1.0
 * @Desc
 */
@ConditionalOnWebApplication
@ConditionalOnProperty(
        name = "spring.datasource.druid.stat-view-servlet.enabled",
        havingValue = "true"
)
public class DruidStatViewServletConfiguration {
    private static final String DEFAULT_ALLOW_IP = "127.0.0.1";

    @Bean
    public ServletRegistrationBean statViewServletRegistrationBean(DruidStatProperties properties) {
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings(new String[]{config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*"});
        if (config.getAllow() != null) {
            registrationBean.addInitParameter("allow", config.getAllow());
        } else {
            registrationBean.addInitParameter("allow", DEFAULT_ALLOW_IP);
        }
        if (config.getDeny() != null) {
            registrationBean.addInitParameter("deny", config.getDeny());
        }
        if (config.getLoginUsername() != null) {
            registrationBean.addInitParameter("loginUsername", config.getLoginUsername());
        }
        if (config.getLoginPassword() != null) {
            registrationBean.addInitParameter("loginPassword", config.getLoginPassword());
        }
        if (config.getResetEnable() != null) {
            registrationBean.addInitParameter("resetEnable", config.getResetEnable());
        }
        return registrationBean;
    }
}
