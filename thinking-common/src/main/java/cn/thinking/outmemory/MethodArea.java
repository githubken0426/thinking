package cn.thinking.outmemory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区
 * 也就是 Non-heap，是用来存储 Object Class Data、常量、静态变量、JIT 编译后的代码等。
 * 如果该区域溢出，则说明某种数据创建的实在是太多了。模拟的话，可以不断创建新的 class，直到溢出为止
 *
 * @author ken
 * 2016-12-9 上午11:53:07
 */
public class MethodArea {
    static class MethodAreaOomObject {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodAreaOomObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args,
                                        MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });
            enhancer.create();
        }
    }
}
