package cn.thinking.proxy;

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
public class ProxyInterceptor implements MethodInterceptor {
	private Logger logger = LogManager.getLogger();
	private String name;

	public ProxyInterceptor(String name) {
		this.name = name;
	}

	/**
	 * 参数：Object为由CGLib动态生成的代理类实例， Method为上文中实体类所调用的被代理的方法引用，
	 * Object[]为参数值列表，MethodProxy为生成的代理类对方法的代理引用。 返回：从代理实例的方法调用返回的值。
	 * 其中，methodProxy.invokeSuper(obj,arg)： 调用代理类实例上的proxy方法的父类方法（即实体类Book中对应的方法）
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy methodProxy) throws Throwable {
		logger.info("MethodInterceptor 调用前:" + method.getName());
		for (Object arg : arg2) {
			System.out.print("参数:" + arg + ",");
		}
		if (!"boss".equals(name)) {// 只有boss才能调用delete方法
			System.out.println("你没有权限！");
			return null;
		}
		Object result = methodProxy.invokeSuper(obj, arg2);// 回调目标方法
		System.out.println("MethodInterceptor 调用后:" + result);
		return result;
	}
}
// public Object getBean(Class<?> cl){
// enhancer.setSuperclass(cl);
// enhancer.setCallback(this);
// return enhancer.create();
// }
