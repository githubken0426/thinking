package org.thinking.boot.el;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySource(name = "elConfig", value = {"classpath:package.json" }, 
				ignoreResourceNotFound = false, encoding = "UTF-8", 
				factory = JsonPropertySourceFactory.class)
@ComponentScan("org.thinking.boot.el")
public class ELConfig {
	@Value("${name}")
	private String name;
	@Value("${version}")
	private String version;

	public void whriteJson() {
		System.out.println(name + version);
	}
}
class JsonPropertySourceFactory implements PropertySourceFactory{
	@Override
	@SuppressWarnings("unchecked")
	public org.springframework.core.env.PropertySource<?>  createPropertySource(String name, EncodedResource resource) throws IOException {
		Map<String, Object> readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
        return new MapPropertySource("json-property", readValue);
	}
}