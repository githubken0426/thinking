package cn.thinking.proxy;

import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.core.EnhancerFactory;
import cn.thinking.proxy.core.interceptor.ProxyMethodInterceptor;
import net.sf.cglib.proxy.Callback;

public class ProxyMain {
	public static void main(String[] args) {
		Callback[] callbacks = new Callback[] { new ProxyMethodInterceptor("boss") };
		Book book1 = EnhancerFactory.getInstance(callbacks, Book.class);
		book1.query();
		/*
		 * book1.create(); 
		 * book1.delete();
		 */
		book1.update(200);
		/*
		 * Book book2 = BookFactory.getInstance(new MyCglibProxy("kisi"));
		 * book2.query(); 
		 * book2.create(); 
		 * book2.delete(); 
		 * book2.update(200);
		 */
	}
}
