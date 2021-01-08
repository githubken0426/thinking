package org.thinking.boot.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import entity.project.Employee;

//@ControllerAdvice
public class ExceptionHandlerAdice {
	/**
	 * 一、全局异常处理
	 * global exception handler
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelView = new ModelAndView("error");
		modelView.addObject("errorMessage", "error");
		System.out.println("@ExceptionHandler");
		return modelView;
	}
	/**
	 * @ModelAttribute作用是绑定键值对到Model中，让全局的@RequestMapping都能获得此键值对的值。
	 */
	/**
	 * 二、全局数据预处理
	 * 2.1、void返回值 
	 * 在请求/employeeList后，populateModel方法在employeeList方法之前先被调用，
	 * 它把请求参数（/employeeList?param=text）加入到一个名为attributeName的model属性中，
	 * 在它执行后employeeList后，返回视图名helloWorld和model已由@ModelAttribute方法生产好了。
	 * 
	 * @param param
	 * @param model
	 */
	@ModelAttribute
	public void populateModel(@RequestParam String param, Model model) {
		model.addAttribute("attributeName", param);
		System.out.println("@ModelAttribute");
	}

	/**
	 * 2.2、返回具体类型 
	 * 全局数据绑定,每个Controller 的接口中，都能够访问这些数据，key=employee,value=Employee
	 * model属性的名称没有指定，它由返回类型隐含表示，如这个方法返回Employee类型，那么这个model属性的名称是employee
	 * 
	 * @return
	 */
	@ModelAttribute()
	public Employee getEmployeeById(@RequestParam Long id) {
		System.out.println("@ModelAttribute()");
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName("july");
		return emp;
	}

	/**
	 * 2.3、注释一个方法的参数 
	 * 参数emp的值来源于getEmployeeById()方法中的model属性。
	 * 此时如果方法体没标注@SessionAttributes("employee"),scope为session， 否则scope为request。
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/employeeList")
	public String getEmployee(@ModelAttribute("employee") Employee emp) {
		return "employeeList";
	}
	/**
	 * 三、全局数据绑定
	 * WebDataBinder用于表单到方法的数据绑定,只对本控制器有效
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}
}
