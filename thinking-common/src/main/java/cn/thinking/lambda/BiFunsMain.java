package cn.thinking.lambda;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * 	在实际使用中，我们往往会输入多个参数，而不是一个参数。
 * 	针对于多个参数的计算，最终都可以拆分两个参数的运算，然后将两个参数的运算结合起来。
 * 	如：1+2+3+4 = 10，可以拆分为1+2 = 3,   3+3=6； 6+4 = 10； 三个步骤完成；
 * 
 * 	接口名	  				说明
 *  BiFunction<T, U, R>   	接收T类型和U类型的两个参数，返回一个R类型的结果
 *  BiPredicate<T, U>		接收T类型和U类型的两个参数，返回Boolean类型的结果
 *  BiConsumer<T , U>		接收T类型和U类型的两个参数，不返回值
 * @author kun.f.wang
 */
public class BiFunsMain {
	
	public static void main(String[] args) {
		String t="abc",u="edf";
		BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length() + str2.length();
		System.out.println(biFunction.apply(t, u));
		
		
		BiPredicate<String, String> biPredicate = (str1, str2) -> str1.length() > str2.length();
		System.out.println(biPredicate.test(t, u));
		
		BiConsumer<String, String> biConsumer = (str1, str2) -> System.out.println(str1 + str2);
		biConsumer.accept(t, u);
	}
}
