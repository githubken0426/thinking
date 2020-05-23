package cn.thinking.proxy.lazyload;

import cn.thinking.proxy.bean.LazyBean;
import cn.thinking.proxy.bean.PropertyBean;

public class LazyMain {
	public static void main(String[] args) {
		//无论调用多次属性，LazyBean只加载一次
		LazyBean bean = new LazyBean("lisi", 23);
		PropertyBean property=bean.getPropertyBean();
		System.out.println("初次调用"+property.getKey());
		System.out.println(property.getValue());
		System.out.println("再次调用"+property.getKey());
		
		System.out.println("-----------------------------------------");
		//调用多少次属性，LazyBean加载多少次
		PropertyBean dispatcher=bean.getPropertyBeanDispatcher();
		System.out.println("初次调用"+dispatcher.getKey());
		System.out.println(property.getValue());
		System.out.println("再次调用"+dispatcher.getKey());
	}
}
