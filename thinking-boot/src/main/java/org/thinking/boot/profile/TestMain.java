package org.thinking.boot.profile;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * servlet3.0以上，设置spring.profiles.default
 * public class AppContextInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //database security key
        System.setProperty("druid.config.decrypt.key", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAPU3jQc3gPpC7PDQ+89h4d4m7tgLrN9QATV5zcoeA/clR0e6WtE8xU+93vzjWVTCaejBFrYXl5+//NYBFv7sTVcCAwEAAQ==");
        servletContext.setInitParameter("spring.profiles.default", "prod");
    	}
	}
 * @author kun.f.wang
 *
 */
public class TestMain {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("dev","sit");
		context.register(BeanConfig.class);
		context.refresh();
		BeanService bean=context.getBean(BeanService.class);
		System.out.println(bean.getContent());
		context.close();
	}
}
