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
 *   Aware子接口                                              |       描述 
 * BeanNameAware                 |  获取容器中 Bean 的名称 
 * BeanFactoryAware              |  获得BeanFactory对象，可以用来检测Bean的作用域
 * ApplicationContextAware       |  获得ApplicationContext对象,可以用来获取所有Bean definition的名字
 * MessageSourceAware            |  获取 Message Source 相关文本信息
 * ApplicationEventPublisherAware|  发布事件 
 * ResourceLoaderAware           |  获得ResourceLoader对象，这样获取外部资源文件
 * 
 * @author kun.f.wang
 *
 */
@Component
public class AwareService implements BeanNameAware, ResourceLoaderAware {
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
