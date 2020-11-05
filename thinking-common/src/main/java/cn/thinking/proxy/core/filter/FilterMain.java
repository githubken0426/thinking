package cn.thinking.proxy.core.filter;

import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.core.EnhancerFactory;
import cn.thinking.proxy.core.fixed.ProxyFixedValue;
import cn.thinking.proxy.core.interceptor.ProxyMethodInterceptor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;

public class FilterMain {
	public static void main(String[] args) {
		MethodInterceptor interceptor = new ProxyMethodInterceptor("boss");
		FixedValue fixed = new ProxyFixedValue();
		CallbackFilter filter = new ProxyCallbackFilter();
		/**
		 * MethodInterceptor:方法拦截器； NoOp.INSTANCE:表示no
		 * operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截；
		 * FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值；
		 */
		Callback[] callbacks = new Callback[] { interceptor, NoOp.INSTANCE, fixed };
		Book book1 = EnhancerFactory.getInstance(callbacks, filter, Book.class);
		book1.create();
		book1.query();
		book1.delete();
		// update的返回值锁定为888
		System.out.println(book1.update(100));

		System.out.println("------------------------------------------------");
		callbacks = new Callback[] { new ProxyMethodInterceptor("kisi"), NoOp.INSTANCE, fixed };
		Book book2 = EnhancerFactory.getInstance(callbacks, filter, Book.class);
		book2.create();
		book2.delete();
		book2.query();
		// update的返回值锁定为888
		System.out.println(book2.update(200));
	}
}
