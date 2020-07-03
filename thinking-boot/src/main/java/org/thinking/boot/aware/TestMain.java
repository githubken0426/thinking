package org.thinking.boot.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		BeanAwareService beanAware = context.getBean(BeanAwareService.class);
		beanAware.outputResult();
		context.close();
	}
}
