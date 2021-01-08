package org.thinking.boot.properties;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@ApplicationScoped
public class Configuration<T> {
	@Resource(name = "classpath:redies.properties")
	private URL propResource;

	private Properties loadProperties(URL propertiesUrl) {
		Properties properties = new Properties();
		try (InputStream inputStream = propertiesUrl.openStream()) {
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static Map<String, Object> test() throws IOException {
		Map<String, Object> map = new HashMap<>();
		ClassPathResource resource = new ClassPathResource("/redies.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		Set<Object> keys = props.keySet();
		for (Object key : keys) {
			System.out.println(key + " : " + props.get(key));
			String str = key.toString().substring(key.toString().lastIndexOf(".") + 1);
			map.put(str, props.get(key));
		}
		return map;
	}

	public static Client setClientValue(Properties pro) throws Exception {
		Client client = new Client();
		Enumeration<String> propertyNames = (Enumeration<String>) pro.propertyNames();
		Method[] methods = Client.class.getMethods();
		while (propertyNames.hasMoreElements()) {
			String element = propertyNames.nextElement();
			Object value = pro.get(element);
			if (null == value || "".equals(value)) {
				continue;
			}
			int prefix = element.indexOf(".");
			String newElement = element.substring(prefix + 1, element.length());
			String s = newElement.substring(0, 1).toUpperCase();
			String methodName = "set" + s + element.substring(prefix + 2, element.length());
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					method.invoke(client, value);
				}
			}
		}
		return client;
	}

	public static <T> T bind(Class<T> clazz, Map<String, Object> map) throws Exception {
		T t = clazz.newInstance();
		Method[] methods = clazz.getMethods();
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (null == value || "".equals(value)) {
				continue;
			}
			String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					method.invoke(t, value);
				}
			}
		}
		return t;
	}

	public static <T> Set<T> getAll(Class<T> clazz, List<Map<String, Object>> mapList) {
		Set<T> set = new HashSet<>();
		try {
			for (Map<String, Object> map : mapList) {
				set.add(bind(clazz, map));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	public static void main(String[] args) throws Exception {
//		ClassPathResource resource = new ClassPathResource("/redies.properties");
//		Properties props = PropertiesLoaderUtils.loadProperties(resource);
//		Client client = setClientValue(props);
//		System.out.println(client);
//		Client client = bind(Client.class, test());

		System.out.println(getAll(Client.class, null));
//		System.out.println(setClientValue(Subject.class,test()));
	}
}
