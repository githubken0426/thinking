package org.thinking.boot.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("org.thinking.boot.bean")
public class BeanConfig {
	@Profile("dev")
	@Bean(initMethod="init",destroyMethod="destroy")
	public BeanWayService createBeanWayService() {
		return new BeanWayService();
	}
	
	@Profile("dev")
	@Bean()
	public JSR250WayService createJSR250WayService() {
		return new JSR250WayService();
	}
}
