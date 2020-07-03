package org.thinking.boot.cors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8091", 
	allowedHeaders = { CorsConst.ALLOWED_HEADS_VALUES }, 
	exposedHeaders = { CorsConst.EXPOSED_HEADS_VALUES },
	methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.HEAD },
	maxAge=3600)
public class CorsController {
	/**
	 * http://localhost:8091/thinking-cors/corsFull?id=11&name=test
	 * 
	 * 如果headers值与请求的必须一致
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(name = "corsProp", // name值 + '#' + 方法的name值,类默认值是：类名所有大写字母拼装,方法默认值是：方法名
			value = { "/corsProp", "/corsFull" }, 
			params = { "id!=1", "name" }, // “id”，值不能为“1”的请求,必须包含name参数
//			headers = { CorsConst.ALLOWED_HEADS_VALUES },
//			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public String fullProperties(HttpServletRequest request) {
		for (String head : CorsConst.ALLOWED_HEADS) {
			System.err.println(head + " :" + request.getHeader(head));
		}
		return request.toString();
	}
}
