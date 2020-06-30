package org.thinking.boot.event;

import org.springframework.context.ApplicationEvent;

public class BeanEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1222634201663798532L;
	private String message;

	public BeanEvent(Object source,String message) {
		super(source);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
