package cn.tswine.dynamic.datasource.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * druid监控配置
 *
 * @Author: silly
 * @Date: 2019/7/12 9:25
 * @Version 1.0
 * @Desc
 */
@ConfigurationProperties("spring.datasource.druid")
public class DruidStatProperties  extends DynamicCommonProperties{
    private String[] aopPatterns;
    private DruidStatProperties.StatViewServlet statViewServlet = new DruidStatProperties.StatViewServlet();
    private DruidStatProperties.WebStatFilter webStatFilter = new DruidStatProperties.WebStatFilter();

    public String[] getAopPatterns() {
        return this.aopPatterns;
    }

    public void setAopPatterns(String[] aopPatterns) {
        this.aopPatterns = aopPatterns;
    }

    public DruidStatProperties.StatViewServlet getStatViewServlet() {
        return this.statViewServlet;
    }

    public void setStatViewServlet(DruidStatProperties.StatViewServlet statViewServlet) {
        this.statViewServlet = statViewServlet;
    }

    public DruidStatProperties.WebStatFilter getWebStatFilter() {
        return this.webStatFilter;
    }

    public void setWebStatFilter(DruidStatProperties.WebStatFilter webStatFilter) {
        this.webStatFilter = webStatFilter;
    }

    /**
     * web相关监控配置参数
     */
    @Getter
    @Setter
    public static class WebStatFilter {
        /**
         * 是否启用
         */
        private boolean enabled;
        private String urlPattern;
        /**
         * 排除不需要的url
         */
        private String exclusions;
        private String sessionStatMaxCount;
        /**
         * 是否关闭session统计功能
         */
        private String sessionStatEnable;
        /**
         * 配置当前session的用户是谁
         */
        private String principalSessionName;
        /**
         * 配置当前的用户是谁
         */
        private String principalCookieName;
        /**
         * 配置是否监控单个url调用的sql列表
         */
        private String profileEnable;
    }

    @Getter
    @Setter
    public static class StatViewServlet {
        /**
         * 是否启用
         */
        private boolean enabled;
        private String urlPattern;
        /**
         * IP白名单
         */
        private String allow;
        /**
         * IP黑名单
         */
        private String deny;
        /**
         * 用户名
         */
        private String loginUsername;
        /**
         * 密码
         */
        private String loginPassword;
        /**
         * 允许清空统计数据
         */
        private String resetEnable;
    }
}
