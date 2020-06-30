package org.thinking.boot.profile;


public class BeanService {
	private String content;

	public BeanService(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}