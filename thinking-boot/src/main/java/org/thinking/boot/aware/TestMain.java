package org.thinking.boot.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		AwareService beanAware = context.getBean(AwareService.class);
		beanAware.outputResult();
		context.close();
	}
}
