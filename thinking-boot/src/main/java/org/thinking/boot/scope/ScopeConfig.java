package org.thinking.boot.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.thinking.boot.scope.ScopeService.ApplicationBean;
import org.thinking.boot.scope.ScopeService.PrototypeBean;
import org.thinking.boot.scope.ScopeService.RequestBean;
import org.thinking.boot.scope.ScopeService.SessionBean;
import org.thinking.boot.scope.ScopeService.SingletonBean;
import org.thinking.boot.scope.ScopeService.WebsocketBean;

@Configuration
@ComponentScan("org.thinking.boot.scope")
public class ScopeConfig {
	/**
	 * singleton指的是每次从同一个IOC容器中返回同一个bean对象，单例的有效范围是IOC容器，而不是ClassLoader
	 * 
	 * @return
	 */
	@Bean(initMethod = "init", destroyMethod = "destroy")
	@Scope(scopeName = "singleton")
	public SingletonBean getSingleton() {
		return new ScopeService.SingletonBean();
	}

	/**
	 * Spring并不会管理设置为prototype的bean的整个生命周期，获取相关bean时，容器会实例化，或者装配相关的prototype-bean实例，然后返回给客户端，
	 * 但不会保存prototype-bean的实例。所以，尽管所有的bean对象都会调用配置的初始化方法，但是prototype-bean并不会调用其配置的destroy方法。
	 * 所以清理工作必须由客户端进行。所以，Spring容器对prototype-bean 的管理在一定程度上类似于 new
	 * 操作，对象创建后的事情将全部由客户端处理。
	 * 
	 * @return
	 */
	@Bean(initMethod = "init", destroyMethod = "destroy")
	@Scope(scopeName = "prototype")
	public PrototypeBean getPrototype() {
		return new ScopeService.PrototypeBean();
	}
	/**
	 * request 代表该bean的作用域为单个请求，请求结束，则bean将被销毁，第二次请求将会创建一个新的bean实例
	 * <aop:scoped-proxy proxy-target-class="false"/>jdk
	 * <aop:scoped-proxy/> cglib
	 * @return
	 */
	@Bean
	@Scope(scopeName = "request")
	public RequestBean getRequest() {
		return new ScopeService.RequestBean();
	}

	@Bean
	@Scope(scopeName = "session")
	public SessionBean getSession() {
		return new ScopeService.SessionBean();
	}
	/**
	 * application作用域，则是针对ServletContext ，有点类似 singleton，但是singleton代表的是每个IOC容器中仅有一个实例，
	 * 而同一个web应用中，是可能会有多个IOC容器的，
	 * 但一个Web应用只会有一个 ServletContext，
	 * 所以 application 才是web应用中货真价实的单例模式。
	 * @return
	 */
	@Bean
	@Scope(scopeName = "application")
	public ApplicationBean Application() {
		return new ScopeService.ApplicationBean();
	}

	@Bean
	@Scope(scopeName = "websocket")
	public WebsocketBean getWebsocket() {
		return new ScopeService.WebsocketBean();
	}
}
