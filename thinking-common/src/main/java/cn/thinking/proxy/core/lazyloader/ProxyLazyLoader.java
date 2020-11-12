package cn.thinking.proxy.core.lazyloader;

import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.bean.PropertyBean;
import net.sf.cglib.proxy.LazyLoader;

/**
   *  对需要延迟加载的对象添加代理，在获取该对象属性时先通过代理类回调方法进行对象初始化。
   *  在不需要加载该对象时，只要不去获取该对象内属性，该对象就不会被初始化了(在CGLib的实现中只要去访问该对象内属性的getter方法，就会自动触发代理类回调）。
 * 
 * Dispatcher在每次访问延迟加载属性时都会触发代理类回调方法
 * LazyLoader只在第一次访问延迟加载属性时触发代理类回调方法
 * @author ken
 * 2016-12-8 下午03:24:49
 */
public class ProxyLazyLoader implements LazyLoader {

    @Override
    public Object loadObject() throws Exception {
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("Before Book LazyLoader");
        propertyBean.setValue(new Book());

        System.out.println("After LazyLoader...");
        return propertyBean;
    }

}
