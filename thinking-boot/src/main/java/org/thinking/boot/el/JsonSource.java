package org.thinking.boot.el;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class JsonSource {
	
	@Value("classpath:package.json")
	private Resource resource;

	public JsonBean getJsonBean() throws IOException {
		return JSON.parseObject(resource.getInputStream(), JsonBean.class);
	}
}
