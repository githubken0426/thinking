package org.thinking.boot.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cross-origin resource sharing
 * 
 * @author kun.f.wang
 *
 */

public class CorsWebMvcConfigurer implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").
			allowedOrigins("*").
			allowedMethods(CorsConst.ALLOWED_MEDHODS).
			allowedHeaders(CorsConst.ALLOWED_HEADS).
			exposedHeaders(CorsConst.EXPOSED_HEADS).
			allowCredentials(true).
			maxAge(3600);
	}
}
