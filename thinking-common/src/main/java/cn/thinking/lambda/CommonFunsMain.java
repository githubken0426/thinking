package cn.thinking.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 常用的函数式接口主要有四种类型，是通过其输入和输出的参数来进行区分的.
 * 		接口名 				说明 
 * Function<T,R> 	接收一个T类型的参数，返回一个R类型的结果
 * Consumer<T> 		接收一个T类型的参数，不返回值 
 * Predicate<T> 	接收一个T类型的参数，返回一个boolean类型的结果
 * Supplier<T> 		不接受参数，返回一个T类型的结果
 * 
 * @author kun.f.wang
 */
public class CommonFunsMain {

	public static void main(String[] args) {
		functionCreate();

//		functionMethod();
	}

	static void functionCreate() {
		Function<String, String> function = item -> item + "_A";

		Consumer<String> consumer = item -> {
			System.out.println(item);
		};
		Predicate<String> predicate = item -> item.length() > 2;

		Supplier<String> supplier = () -> new String("不接受参数，返回一个T类型的结果");
		System.out.println(supplier.get());
		
		System.out.println("*******************");
		Supplier<Integer> supplierI = new Random(10)::nextInt;
		Stream.generate(supplierI).map(value -> value + 1).limit(5).forEach(System.out::println);

		System.out.println("*******************");
		List<String> list = Arrays.asList("zhangsan", "lisi", "wangwu", "xiaoming", "zhaoliu");
		list.stream().map(value -> value + "_B") // 传入的是一个Function函数式接口
				.filter(value -> value.length() > 2) //	传入的是一个Predicate函数式接口
				.forEach(value -> System.out.println(value)); // 传入的是一个Consumer函数式接口

		System.out.println("*******************");
		list.stream().map(function).filter(predicate).forEach(consumer);
	}
	/**
	 * compose和andThen均是一种链式组合调用；
	 */
	static void functionMethod() {
		System.out.println("*********Function**********");
		Function<String, String> functionA = item -> "functionA " + item;
		Function<String, String> functionB = item -> "functionB " + item;
		/**
		 * 	andThen是先执行自身的apply方法(此时返回： functionA_andThen)；
		 * 	然后将apply的返回值作为after接口的输入值。	
		 * 	最终输出：functionB_functionA_andThen
		 */
		System.out.println(functionA.andThen(functionB).apply("andThen"));
		/**
		 *  compose方法与andThen正好相反。
		 * 	compose方法先执行before(即compose的输入参数)接口的apply方法(此时返回： functionB_compose)。
		 * 	然后将before方法执行的返回值作为compose中apply方法的输入参数。
		 * 	最终输出：functionA_functionB_compose
		 */
		System.out.println(functionA.compose(functionB).apply("compose"));
		
		System.out.println("*********BiFunction**********");
		/**
		 * 	biFunction只有andThen方法，这是有bi类型接口的特征决定的。
		 * 	bi类型的接口需要接收两个参数，然而java中是没有返回两个参数的情况的，
		 * 	所以只有andThen方法，且其参数是function类型的，接收的是一个参数，返回一个值
		 */
		Function<String, String> functionC = item -> {
			System.out.println("Function param: " + item);
			return item;
		};
		BiFunction<String, String, String> biFunction = (str1, str2) -> {
			String result = str1 + " * " + str2;
			System.out.println("BiFunction params: [" + str1 + "," + str2 + "], result: [" + result + "]");
			return result;
		};
		System.out.println(biFunction.andThen(functionC).apply("functionC", "biFunction"));
		
		System.out.println("*********Consumer**********");
		/**
		 * 	将输入参数分别赋给andThen内部的accept方法和after内部的accept方法。
		 * 	after的计算在andThen之后，起到了后置连接的作用。
		 * 	consumer没有compose方法，因为后置连接反过来就是前置连接，所以不需要一个多余的compose方法了。
		 * 	只需要在传递时，交换两个consumer的顺序即可。
		 * 
		 * 	注意：两个consumer的接收类型必须一致
		 */
		Consumer<String> consumerA = item -> {
			item = "consumerA " + item;
			System.out.println(item);
		};
		Consumer<String> consumerB = item -> {
			item = "consumerB " + item;
			System.out.println(item);
		};
		consumerA.andThen(consumerB).accept("andThen");
		//	交换两个consumer的顺序达到compose效果
		consumerB.andThen(consumerA).accept("as compose");
		
		System.out.println("*********BiConsumer**********");
		BiConsumer<String, String> biConsumerA = (str1, str2) -> System.out.println(str1 + " - " + str2);
		BiConsumer<String, String> biConsumerB = (str1, str2) -> System.out.println(str1 + " * " + str2);
		biConsumerA.andThen(biConsumerB).accept("biConsumerA", "biConsumerB");
		
		System.out.println("*********Predicate**********");
		Predicate<String> predicate = item -> item.length() > 2;
		Predicate<String> other = item -> item.length() > 0;
		System.out.println(predicate.or(other).test("A"));
		System.out.println(predicate.and(other).test("A"));
		System.out.println(predicate.negate().and(other).test("A"));
		
		Supplier<String> supplier = () -> new String("不接受参数，返回一个T类型的结果");
		System.out.println(supplier.get());
	}
}
