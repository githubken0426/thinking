package cn.thinking.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: kun.f.wang
 * @Date: 6/1/2020 10:00
 * @Description:
 */
@RestController(value = "/common")
public class CommonController {

	@GetMapping(name = "fullProperties", //类上的name值 + '#' + 方法的name值,类默认值是：类名所有大写字母拼装,方法默认值是：方法名
				value = { "/fullProperties", "/properties" }, 
				params = {"id!=1","name" }, //仅处理请求中包含了名为“id”，值不能为“1”的请求,必须包含name参数
				headers = {"security=token1234" }, 
				consumes = { "application/json" }, 
				produces = { "application/json" })
	public String fullProperties(HttpServletRequest request,String id, String name) {
		System.err.println(id + ", " + name + " ,header:" + request.getHeader("security"));
		return request.toString();
	}

	@PostMapping(value = "/")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

		return "login";
	}
}
