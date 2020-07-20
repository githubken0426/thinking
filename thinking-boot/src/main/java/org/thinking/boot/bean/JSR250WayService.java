package org.thinking.boot.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250WayService {
	
	@PostConstruct
	public void init() {
		System.out.println("* JSR250WayService @PostConstruct init");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("* JSR250WayService @PreDestroy destroy");
	}
	
}
