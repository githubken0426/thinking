package cn.thinking.lambda;

import cn.thinking.lambda.functional.ProduceService;

public class ProduceMain {
	public static void main(String[] args) {
		/*
		 * 1、lambda表达式:这种形式最为直观，lambda表达式，接收一个String类型的参数。
		 */
		ProduceService produce = item -> item + " item";
		System.out.println(produce.info("test"));

		System.out.println("**************************");
		/**
		 * 2、方法引用：
		 *  ProduceMain方法当中的getInstance和getMessage方法接收一个参数，返回一个结果。符合函数式接口ProduceService的定义。 
		 * 	函数式接口只是定义了个方法的约定， 而对于方法内部进行何种操作则并没有做任何的限制。在这点上，跟java以前的版本中的实现类与接口之间的关系很类似。
		 * 	不同的是，函数式接口更偏重于计算过程，约束了一个计算过程的输入和输出。 
		 */
		ProduceService produceInstance = ProduceMain::getInstance;//方法引用
		System.out.println(produceInstance.info("getInstance"));
		ProduceService produceMessage = ProduceMain::getMessage;
		System.out.println(produceMessage.info("getMessage"));
		
		System.out.println("**************************");
		/**
		 * 3、构造器方法引用：
		 * 构造函数的结构：接收输入参数，然后返回一个对象。这种约束跟函数式接口的约束很像。
		 * 所以只要“输入参数类型”与“输出参数类型”跟ProduceService中的方法约束相同，就可以创建出ProduceService接口的实例；
		 * 如下，String的构造方法new String(str)，所以可以得到实例。
		 * 这里存在一个类型推断的问题，JDK的编译器已经帮我们自动找到了只有一个参数，且是String类型的构造方法。
		 * 这就是我们直接String::new，没有指定使用哪一个构造方法，却可以创建实例的原因。
		 */
		ProduceService produceNew = String::new;//构造器方法引用
		System.out.println(produceNew.info("getInstance"));
		System.out.println(produceNew.info("getMessage"));
		System.out.println("**************************");
	}

	public static String getInstance(String item) {
		return item + "世界！";
	}

	public static String getMessage(String massage) {
		return "世界," + massage + "!";
	}
}
