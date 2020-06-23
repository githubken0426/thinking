package org.thinking.boot.scope;

import org.springframework.stereotype.Component;

/**
 * https://docs.spring.io/spring/docs/4.3.25.RELEASE/spring-framework-reference/htmlsingle/
 * Table 7.3. Bean scopes
	Scope	 Description
	singleton (Default) Scopes a single bean definition to a single object instance per Spring IoC container.
	
	prototype Scopes a single bean definition to any number of object instances.
	
	request Scopes a single bean definition to the lifecycle of a single HTTP request; 
			that is, each HTTP request has its own instance of a bean created off the back of a single bean definition. 
			Only valid in the context of a web-aware Spring ApplicationContext.
	
	session Scopes a single bean definition to the lifecycle of an HTTP Session. 
			Only valid in the context of a web-aware Spring ApplicationContext.
	
	globalSession Scopes a single bean definition to the lifecycle of a global HTTP Session. 
				  Typically only valid when used in a Portlet context. 
				  Only valid in the context of a web-aware Spring ApplicationContext.
	
	application   Scopes a single bean definition to the lifecycle of a ServletContext. 
				  Only valid in the context of a web-aware Spring ApplicationContext.
	
	websocket     Scopes a single bean definition to the lifecycle of a WebSocket. 
				  Only valid in the context of a web-aware Spring ApplicationContext.
 * @author kun.f.wang
 */

@Component
public class ScopeService {
	
	public static class SingletonBean {
		public void init(){
			System.out.println("SingletonBean init ");
		}

		public void destroy(){
			System.out.println("SingletonBean destroy ");
		}
	}

	public static class PrototypeBean {
		public void init(){
			System.out.println("PrototypeBean init ");
		}

		public void destroy(){
			System.out.println("PrototypeBean destroy ");
		}
	}

	public static class RequestBean {

	}

	public static class SessionBean {

	}
	public static class ApplicationBean {

	}

	public static class WebsocketBean {

	}
}
