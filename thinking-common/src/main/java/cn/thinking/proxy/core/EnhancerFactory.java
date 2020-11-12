package cn.thinking.proxy.core;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Enhancer是CGLib中的一个字节码增强器， 它可以方便的对你想要处理的类进行扩展
 * 
 * @author ken 2016-12-7 上午09:42:40
 */
public class EnhancerFactory {

	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Callback callback, Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		return (T) Enhancer.create(clazz, callback);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Callback[] callbacks, Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallbacks(callbacks);
		return (T) enhancer.create();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Callback[] callbacks, CallbackFilter filter, Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallbackFilter(filter);
		enhancer.setCallbacks(callbacks);
		return (T) enhancer.create();
	}
}
