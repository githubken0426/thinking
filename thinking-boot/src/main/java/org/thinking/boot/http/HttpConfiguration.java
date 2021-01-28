package org.thinking.boot.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class HttpConfiguration {

	private static final int DEFAULT_MAX_IN_MEMORY_SIZE = 1048576;
	private static final int DEFAULT_MAX_UPLOAD_SIZE = 20971520;

	@Bean
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxInMemorySize(DEFAULT_MAX_IN_MEMORY_SIZE);
		multipartResolver.setMaxUploadSize(DEFAULT_MAX_UPLOAD_SIZE);
		return multipartResolver;
	}

}
