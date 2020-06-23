package org.thinking.boot.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.thinking.boot.scope.ScopeService.PrototypeBean;
import org.thinking.boot.scope.ScopeService.SingletonBean;

public class ScopeMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
		SingletonBean sington1 = context.getBean(SingletonBean.class);
		SingletonBean sington2 = context.getBean(SingletonBean.class);
		System.out.println(sington1.equals(sington2));

		PrototypeBean prototype = context.getBean(PrototypeBean.class);
		PrototypeBean prototype2 = context.getBean(PrototypeBean.class);
		System.out.println(prototype.equals(prototype2));
//
//		SessionBean session = context.getBean(SessionBean.class);
//		SessionBean session2 = context.getBean(SessionBean.class);
//		System.out.println(session.equals(session2));
		context.close();
	}
}
