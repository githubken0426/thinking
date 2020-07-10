package org.thinking.boot.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfigura.class);

		System.out.println(context.getEnvironment().getProperty("os.name") + ": "
				+ context.getBean(CommandService.class).showCommand());
		context.close();
	}
}
