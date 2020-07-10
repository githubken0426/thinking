package org.thinking.boot.annotation.combination;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CombinationConfigura.class);
		context.close();
	}
}
