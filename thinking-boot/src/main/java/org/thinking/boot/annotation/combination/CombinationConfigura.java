package org.thinking.boot.annotation.combination;

import org.springframework.context.annotation.Bean;

@CombinationAnnotation(basePackages= {"org.thinking.boot.annotation.combination"})

public class CombinationConfigura {
	@Bean
	public Object objectService() {
		System.out.println("objectService");
		return new Object();
	}
}
