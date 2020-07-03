package org.thinking.boot.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("org.thinking.boot.profile")
public class ProfileConfig {
	
	@Bean
	@Profile({"dev","sit"})
	public BeanService beanDevService() {
		return new BeanService("dev");
	}
	
	@Bean
	@Profile({"prod"})
	public BeanService beanProdService() {
		return new BeanService("prod");
	}
}
