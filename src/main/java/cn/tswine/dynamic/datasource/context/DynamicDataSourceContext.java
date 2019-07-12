package cn.tswine.dynamic.datasource.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 动态切换数据源操作上下文，用来修改和获取当前线程使用的数据源标签
 *
 * @Author: silly
 * @Date: 2019/7/11 14:45
 * @Version 1.0
 * @Desc
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DynamicDataSourceContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 提供给AOP去设置当前的线程的数据源的信息
     *
     * @param label
     */
    public static void setDSLabel(String label) {
        contextHolder.set(label);
    }

    /**
     * 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
     *
     * @return
     */
    public static String getDSLabel() {
        return contextHolder.get();
    }

    /**
     * 使用默认的数据源
     */
    public static void clear() {
        contextHolder.remove();
    }


}
