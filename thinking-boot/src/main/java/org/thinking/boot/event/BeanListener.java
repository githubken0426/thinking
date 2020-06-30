package org.thinking.boot.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BeanListener implements ApplicationListener<BeanEvent> {

	@Override
	public void onApplicationEvent(BeanEvent event) {
		System.out.println(BeanListener.class + ":" + event.getMessage());
	}

}
