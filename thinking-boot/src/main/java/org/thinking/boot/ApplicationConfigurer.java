package org.thinking.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thinking.boot.jwt.interceptor.JwtAuthenticationInterceptor;

@Configuration
@ComponentScan("org.thinking.boot.jwt")
public class ApplicationConfigurer implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 默认拦截所有路径
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public JwtAuthenticationInterceptor authenticationInterceptor() {
		System.out.println("JwtAuthenticationInterceptor");
		return new JwtAuthenticationInterceptor();
	}
}
