package cn.thinking.lambda.functional;

import java.util.Arrays;

/**
 * 	必须注解在接口上
 * 	被注解的接口有且只有一个抽象方法
 * 	被注解的接口可以有默认方法/静态方法，或者重写Object的方法
 * @Auther: ken.wangTJ
 * @Date: 7/9/2019 14:04
 * @Description:
 */

@FunctionalInterface
public interface J8SampleService {

    default void defaultMethod() {
        Arrays.stream(J8SampleService.class.getDeclaredMethods()).filter(
                method -> method.getName().equals("defaultMethod")
        ).forEach(System.out::println);
    }

    static void staticMethod() {
        Arrays.stream(J8SampleService.class.getDeclaredMethods()).filter(
                method -> method.getName().equals("staticMethod")
        ).forEach(System.out::println);
    }

    void methodB(String param);
}
