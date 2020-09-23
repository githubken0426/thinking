package org.thinking.boot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class PropertiesReader {
	private String sayWhat;
	private String toWho;

	public String getSayWhat() {
		return sayWhat;
	}

	public void setSayWhat(String sayWhat) {
		this.sayWhat = sayWhat;
	}

	public String getToWho() {
		return toWho;
	}

	public void setToWho(String toWho) {
		this.toWho = toWho;
	}
}
