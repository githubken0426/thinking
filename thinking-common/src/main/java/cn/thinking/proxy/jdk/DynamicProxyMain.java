package cn.thinking.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 * 
 * @author Administrator 2015年9月14日
 */
public class DynamicProxyMain {

	public static void main(String[] args) {
		// 真实代理对象
		Subject realSubject = new RealSubject();
		// 传入真实的对象
		InvocationHandler handler = new DynamicProxy(realSubject);

		/**
		 * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
		 * 
		 * 第一个参数 handler.getClass().getClassLoader() ，
		 * 我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
		 * 
		 * 第二个参数realSubject.getClass().getInterfaces()，
		 * 我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象， 这样我就能调用这组接口中的方法了
		 * 
		 * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
		 */
		/**
		 * 可能我以为返回的这个代理对象会是Subject类型的对象，或者是InvocationHandler的对象，
		 * 结果却不是，首先我们解释一下为什么我们这里可以将其转化为Subject类型的对象？
		 * 原因就是在newProxyInstance这个方法的第二个参数上，我们给这个代理对象提供了一组什么接口，
		 * 那么我这个代理对象就会实现了这组接口，这个时候我们当然可以将这个代理对象强制类型转化为
		 * 这组接口中的任意一个，因为这里的接口是Subject类型，所以就可以将其转化为Subject类型了。
		 */

		Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
				realSubject.getClass().getInterfaces(), handler);
		System.out.println("Main:" + subject.getClass().getName());
		subject.rent();
		subject.cons("rent yet!");
	}
}
