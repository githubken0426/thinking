package org.thinking.boot.event;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BeanListener implements ApplicationListener<BeanEvent> {
	@PostConstruct
	public void init() {
		System.out.println("BeanListener init");
	}

	@Override
	public void onApplicationEvent(BeanEvent event) {
		System.out.println(BeanListener.class + ":" + event.getMessage());
	}

}
