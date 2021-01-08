package org.thinking.boot.jwt.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thinking.boot.jwt.annotation.JwtTokenRequired;
import org.thinking.boot.jwt.controller.JwtComponent;
import org.thinking.boot.jwt.enumration.IssuerEunm;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtComponent jwtComponent;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("preHandle 1:" + object);
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 沒有JwtTokenRequired注解，跳过认证
		if (!method.isAnnotationPresent(JwtTokenRequired.class)) {
			return true;
		}
		JwtTokenRequired jwtToken = method.getAnnotation(JwtTokenRequired.class);
		if (jwtToken.isRequired()) {
			String token = request.getHeader("token");// 从请求头中取出 token,执行认证
			if (token == null) {
				throw new Exception("error: token is null");
			}
			boolean verifyResult = jwtComponent.verifyToken(token,IssuerEunm.SINA);// 验证 token
			if (!verifyResult) {
				throw new Exception("error: verify fail");
			}
			// 获取载荷内容
			Map<String, String> payload = jwtComponent.dumpToken(token);
			String userName = payload.get("userName");
			String password = payload.get("password");
			// 放入attribute以便后面调用
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			return verifyResult;
		}
		return true;
	}
}
