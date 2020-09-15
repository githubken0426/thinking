package org.thinking.boot.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class BeanAwareService
		implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ResourceLoaderAware, ApplicationContextAware,
		ApplicationEventPublisherAware, MessageSourceAware {

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
	public void setMessageSource(MessageSource messageSource) {
		System.out.println("*6 MessageSource's setMessageSource");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("*7 ApplicationContextAware's setApplicationContext");
	}

	@PostConstruct
	public void init() {
		System.out.println("*8 @PostConstruct init");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("*9 @PreDestroy destroy");
	}
}
