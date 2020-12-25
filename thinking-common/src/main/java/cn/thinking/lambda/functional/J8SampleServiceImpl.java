package cn.thinking.lambda.functional;

import java.util.Arrays;

/**
 * @Auther: ken.wangTJ
 * @Date: 7/9/2019 14:11
 * @Description:
 */
public class J8SampleServiceImpl implements J8SampleService {

	@Override
	public void methodB(String param) {
		System.out.println("param:" + param);
		Arrays.stream(J8SampleService.class.getDeclaredMethods())
			.filter(method -> method.getName().equals("methodB"))
			.forEach(System.out::println);
	}
}
