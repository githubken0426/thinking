package org.thinking.boot.el;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ELMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ELConfig.class);
		ELConfig el = context.getBean(ELConfig.class);
		el.whriteJson();
		JsonBean bean = context.getBean(JsonSource.class).getJsonBean();
		System.out.println(bean);
		context.close();
	}
}
