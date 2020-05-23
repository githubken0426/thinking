package cn.thinking.proxy.filter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * cglib过滤器
 *
 * @author Administrator
 * 2016-1-5 下午01:29:10
 */
public class ProxyCallbackFilter implements CallbackFilter {
    /**
     * 回调过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置
     * 返回的值被代理类的各个方法在回调数组Callback[]中的位置索引
     * 0[对应proxy]/1[对应NoOp.INSTANCE]/2[对应new TargetResultFixed()]
     */
    @Override
    public int accept(Method arg0) {
        if ("delete".equalsIgnoreCase(arg0.getName()))
            return 0;
        if ("update".equalsIgnoreCase(arg0.getName())) {
            System.out.println("Filter update ==2");
            return 2;
        }
        return 1;
    }

}
