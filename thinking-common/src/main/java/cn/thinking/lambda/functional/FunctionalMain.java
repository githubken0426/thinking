package cn.thinking.lambda.functional;

/**
 * @Auther: ken.wangTJ
 * @Date: 7/9/2019 14:14
 * @Description:
 */
public class FunctionalMain {
	public static void main(String[] args) {
		J8SampleService j8Sample = new J8SampleServiceImpl();
		j8Sample.defaultMethod();
		J8SampleService.staticMethod();
		j8Sample.methodB("Test unit one!");

		System.out.println("**************************");
		J8SampleService j8Sample2 = System.out::println;
		// print null,invoke functional,not J8SampleServiceImpl
		j8Sample2.methodB("Test unit Two!");
		System.out.println("**************************");
		
		j8Sample2 = item -> System.out.println(item);;
		j8Sample2.methodB("Test unit Three!");
	}
}
