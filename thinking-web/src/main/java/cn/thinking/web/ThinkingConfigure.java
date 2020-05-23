package cn.thinking.web;

import cn.thinking.web.aop.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class ThinkingConfigure implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Value("${global.fileRequestUrl}")
    private String fileRequestUrl;
    @Value("${global.uploadPath}")
    private String uploadPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**", fileRequestUrl).
                addResourceLocations("classpath:/static/", "file:" + uploadPath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout", "/error", "/ccps_7_H-ui.3.0/**",
                        "/css/**", "/fonts/**", "/images/**", "/jQuery/**", "/layer/**",
                        "/kkpager/**", "/files/**");
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }
}