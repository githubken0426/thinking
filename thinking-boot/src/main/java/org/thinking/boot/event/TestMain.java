package org.thinking.boot.event;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		
		BeanPublish publish=context.getBean(BeanPublish.class);
		publish.publish();
		context.close();
	}
}
