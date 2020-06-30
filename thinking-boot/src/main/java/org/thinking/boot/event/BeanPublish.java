package org.thinking.boot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanPublish {
	@Autowired
	private ApplicationContext context;

	@Bean
	public BeanEvent beanEvent() {
		return new BeanEvent(this, "test publish");
	}

	public void publish() {
		context.publishEvent(beanEvent());
	}
}
