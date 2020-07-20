package org.thinking.boot.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class BeanAwareService implements  BeanNameAware, BeanFactoryAware, ApplicationContextAware,
		ApplicationEventPublisherAware, ResourceLoaderAware, BeanClassLoaderAware {
	
	
	@Override
	public void setBeanName(String name) {
		System.out.println("*1 BeanNameAware's setBeanName");
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("*2 BeanClassLoaderAware's setBeanClassLoader");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("*3 BeanFactoryAware's setBeanFactory");
	}
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("*4 ResourceLoaderAware's setResourceLoader");
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("*5 ApplicationEventPublisherAware's setApplicationEventPublisher");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("*6 ApplicationContextAware's setApplicationContext");
	}

}
