package org.thinking.boot.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanService implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("* InitializingBean's afterPropertiesSet");
	}

}
