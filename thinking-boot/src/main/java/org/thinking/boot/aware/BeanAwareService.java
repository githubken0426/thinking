package org.thinking.boot.aware;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Aware子接口 描述 BeanNameAware 获取容器中 Bean 的名称 BeanFactoryAware 获取当前 BeanFactory
 * ，这样可以调用容器的服务 ApplicationContextAware 同上，在BeanFactory 和 ApplicationContext 的区别
 * 中已明确说明 MessageSourceAware 获取 Message Source 相关文本信息
 * ApplicationEventPublisherAware 发布事件 ResourceLoaderAware 获取资源加载器，这样获取外部资源文件
 * 
 * @author kun.f.wang
 *
 */
@Component
public class BeanAwareService implements BeanNameAware, ResourceLoaderAware {
	private String name;
	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	public void outputResult() {
		System.out.println("bean name:" + name);
		Resource resource = resourceLoader.getResource("classpath:package.json");
		try {
			String result = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
