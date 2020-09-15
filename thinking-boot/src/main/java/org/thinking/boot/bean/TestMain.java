package org.thinking.boot.bean;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 
	public interface BeanPostProcessor {
	    Object postProcessBeforeInitialization(Object var1, String var2) throws BeansException;
	
	    Object postProcessAfterInitialization(Object var1, String var2) throws BeansException;
	}
 * 
 * 	postProcessBeforeInitialization
 * 	首先@PostConstruct 会被最先调用
	其次 InitializingBean.afterPropertiesSet()方法将会被调用 
	最后调用通过 XML 配置的 init-method 方法或通过设置 @Bean 注解 设置 initMethod 属性的方法
	postProcessAfterInitialization
 * 
 * Bean容器找到配置文件中Spring Bean的定义。
 * Bean容器利用Java Reflection API创建一个Bean的实例。
   *   如果涉及到一些属性值 利用set方法设置一些属性值。
   *   如果Bean实现了BeanNameAware接口，调用setBeanName()方法，传入Bean的名字。
   *   如果Bean实现了BeanClassLoaderAware接口，调用setBeanClassLoader()方法，传入ClassLoader对象的实例。
   *   如果Bean实现了BeanFactoryAware接口，调用setBeanFactory()方法，传入BeanFactory对象的实例。
   *   与上面的类似，如果实现了其他*Aware接口，就调用相应的方法。
 * 
   * 如果有和加载这个Bean的Spring容器相关的BeanPostProcessor对象，执行postProcessBeforeInitialization()方法
   * 调用PostConstruct注解标注的方法
   * 如果Bean实现了InitializingBean接口，执行afterPropertiesSet()方法。
   * 如果Bean在配置文件中的定义包含init-method属性，执行指定的方法。
   * 如果有和加载这个Bean的Spring容器相关的BeanPostProcessor对象，执行postProcessAfterInitialization()方法
 * 
   * 当要销毁Bean的时候，如果Bean实现了DisposableBean接口，执行destroy()方法。
   * 当要销毁Bean的时候，如果Bean在配置文件中的定义包含destroy-method属性，执行指定的方法。
 * 
 * @author kun.f.wang 详细见图spring-bean-life.ong/bean.jpg
 */
public class TestMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		context.getBean(BeanWayService.class);
		context.close();
		System.out.println(context.getClass().getName());
	}
}
