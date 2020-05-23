package cn.thinking.web.aop;

import cn.thinking.web.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session验证
 * 
 * @author Administrator
 * 
 */
@Configuration
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		System.out.println("SessionInterceptor.preHandle():" + url);
		if(url.contains("v1")){
			return true;
		}
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if (loginUser == null) {
			String path = request.getContextPath();
			response.sendRedirect(path + "/logout");
			return false;
		}
		return true;
	}

}
