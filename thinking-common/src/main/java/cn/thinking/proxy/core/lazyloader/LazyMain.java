package cn.thinking.proxy.core.lazyloader;

import cn.thinking.proxy.bean.LazyBean;
import cn.thinking.proxy.bean.PropertyBean;
import cn.thinking.proxy.core.dispatcher.ProxyDispatcher;
import net.sf.cglib.proxy.Enhancer;

public class LazyMain {
	public static void main(String[] args) {
		PropertyBean dispatcher = (PropertyBean) Enhancer.create(PropertyBean.class, new ProxyDispatcher());
		PropertyBean lazyLoader = (PropertyBean) Enhancer.create(PropertyBean.class, new ProxyLazyLoader());

		/**
		  * 无论调用多次属性，LazyBean只加载一次
		 */
		LazyBean bean = new LazyBean("lisi", 23, lazyLoader, dispatcher);
		PropertyBean lazyLoaderBean = bean.getLazyloader();
		System.out.println("初次调用" + lazyLoaderBean.getKey());
		System.out.println(lazyLoaderBean.getValue());
		System.out.println("再次调用" + lazyLoaderBean.getKey());

		System.out.println("-----------------------------------------");
		/**
		  * 调用多少次属性，LazyBean加载多少次
		 */
		PropertyBean dispatcherBean = bean.getDispatcher();
		System.out.println("初次调用,key=" + dispatcherBean.getKey());
		System.out.println(lazyLoaderBean.getValue());
		System.out.println("再次调用" + dispatcherBean.getKey());
	}
}
