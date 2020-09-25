package org.thinking.boot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PropertiesReader.class)
//switch,enable,flag都为true才加载
@ConditionalOnProperty(prefix = "properties", name = "{switch,enable,flag}", havingValue = "true")
/**
 * 表达式条件注入：依赖SPEL表达式。
 * 	条件与和嵌套${switch:false} || ${enable:false},${switch:${enable:false}}
 */
@ConditionalOnExpression(value = "${switch:false} || ${enable:false}")
public class ReaderConfig {
	@Autowired
	private PropertiesReader propertiesReader;

//	@Bean()
//	public Object getReaderService() {
//		return new ReaderService(propertiesReader.getSayWhat(), propertiesReader.getToWho());
//	}
}
