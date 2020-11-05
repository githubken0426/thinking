package cn.thinking.proxy.core.interceptor;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 动态代理：权限限制
 * 
 * @author Administrator 2016-1-5 上午11:54:09
 */
public class ProxyMethodInterceptor implements MethodInterceptor {
	private Logger logger = LogManager.getLogger();
	private String name;

	public ProxyMethodInterceptor(String name) {
		this.name = name;
	}

	/**
	 *  Object为由CGLib动态生成的代理类实例，
	 *  Method为上文中实体类所调用的被代理的方法引用， 
	 *  Object[]为参数值列表，
	 *  MethodProxy为生成的代理类对方法的代理引用。 
	   *  返回：从代理实例的方法调用返回的值。
	   *  其中，methodProxy.invokeSuper(obj,arg)： 调用代理类实例上的proxy方法的父类方法（即实体类Book中对应的方法）
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy methodProxy) throws Throwable {
		for (Object arg : arg2) {
			System.out.print("参数：" + arg + ",");
		}
		if (method.getName().equals("delete") && !"boss".equals(name)) {
			logger.info("你没有权限！");
			return null;
		}
		Object result = methodProxy.invokeSuper(obj, arg2);//1 回调目标方法
		logger.info("调用方法：" + method.getName()+",返回值：" + result);
		return result;
	}
}