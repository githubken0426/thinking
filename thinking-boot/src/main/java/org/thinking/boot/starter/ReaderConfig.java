package org.thinking.boot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PropertiesReader.class)
@ConditionalOnProperty(prefix = "properties", name = "switch", havingValue = "true")
@ComponentScan("properties")
public class ReaderConfig {
	@Autowired
	private PropertiesReader propertiesReader;

//	@Bean()
//	public Object getReaderService() {
//		return new ReaderService(propertiesReader.getSayWhat(), propertiesReader.getToWho());
//	}
}
