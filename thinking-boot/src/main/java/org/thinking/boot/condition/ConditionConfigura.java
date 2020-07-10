package org.thinking.boot.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "org.thinking.boot.condition", "org.thinking.boot.condition" })
public class ConditionConfigura {
	@Bean
	@Conditional(value = { WindowsCondition.class })
	public CommandService windowsService() {
		return new WindowsCommandService();
	}

	@Bean
	@Conditional(LinuxCondition.class)
	public CommandService linuxService() {
		return new LinuxCommandService();
	}
}
