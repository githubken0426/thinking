package org.thinking.boot.bean;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		context.getBean(BeanWayService.class);
		context.getBean(JSR250WayService.class);
		context.close();
	}
}
