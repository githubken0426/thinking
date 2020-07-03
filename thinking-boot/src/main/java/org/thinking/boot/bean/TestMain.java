package org.thinking.boot.bean;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
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
 * @author kun.f.wang
 *
 */
public class TestMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		context.getBean(JSR250WayService.class);
		context.getBean(InitializingBean.class);
		context.getBean(BeanWayService.class);
		context.close();
	}
}
