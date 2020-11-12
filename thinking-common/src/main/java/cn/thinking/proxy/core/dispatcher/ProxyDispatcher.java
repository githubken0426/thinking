package cn.thinking.proxy.core.dispatcher;

import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.bean.PropertyBean;
import net.sf.cglib.proxy.Dispatcher;

/**
 * Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法.
 * @author ken 2016-12-8 下午03:26:49
 */
public class ProxyDispatcher implements Dispatcher {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("Before Dispatcher...!");
		PropertyBean propertyBean = new PropertyBean();
		propertyBean.setKey("book_key");
		propertyBean.setValue(new Book());
		System.out.println("After Dispatcher...!");
		return propertyBean;
	}
}
