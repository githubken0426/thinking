package cn.thinking.proxy.filter;

import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.ProxyInterceptor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class FilterFactory {
    //private static Enhancer en = null;

    /**
     * @param proxy 方法拦截器
     * @return 2016-12-7 上午11:08:33
     */
    public static Book getInstanceByFilter(ProxyInterceptor proxy) {
//		//原代码：问题出现点
//		 if(en==null)
//			 en=new Enhancer();
        Enhancer en = new Enhancer();//修改后
        en.setSuperclass(Book.class);
        /**
         * setCallbacks中定义了所使用的拦截器，
         * 其中NoOp.INSTANCE是CGlib所提供的实际是一个没有任何操作的拦截器，
         * 他们是有序的,一定要和CallbackFilter(即MyProxyFilter)里返回值顺序一致。
         * MyProxyFilter中return返回(0/1/2)的就是返回的顺序
         */
        Callback[] callbacks = new Callback[]{proxy, NoOp.INSTANCE, new TargetResultFixed()};
        en.setCallbacks(callbacks);
        en.setCallbackFilter(new ProxyCallbackFilter());
        return (Book) en.create();
    }
}
