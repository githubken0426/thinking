package org.thinking.boot.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn(value = {"beanListener"})//定义Bean初始化及销毁时的顺序
public class BeanPublish {
	@Autowired
	private ApplicationContext context;
	
	@PostConstruct
	public void init() {
		System.out.println("BeanPublish init");
	}
	
	@Bean
	public BeanEvent beanEvent() {
		return new BeanEvent(this, "test publish");
	}

	public void publish() {
		context.publishEvent(beanEvent());
	}
}
