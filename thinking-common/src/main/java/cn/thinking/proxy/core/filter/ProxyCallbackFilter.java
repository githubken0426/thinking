package cn.thinking.proxy.core.filter;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * cglib过滤器
 *
 * @author Administrator 2016-1-5 下午01:29:10
 */
public class ProxyCallbackFilter implements CallbackFilter {
	/**
	 * CallbackFilter：设置对不同方法执行不同的回调逻辑，或者根本不执行回调。
	 * 回调过滤方法 返回的值为数字，代表了Callback[]中的索引:
	 * 0[MethodInterceptor],1[对应NoOp.INSTANCE],2[对应 FixedValue]
	 */
	@Override
	public int accept(Method arg0) {
		if ("delete".equalsIgnoreCase(arg0.getName()))
			return 0;
		if ("update".equalsIgnoreCase(arg0.getName())) {
			System.out.println("Filter update,index = 2");
			return 2;
		}
		return 1;
	}

}
