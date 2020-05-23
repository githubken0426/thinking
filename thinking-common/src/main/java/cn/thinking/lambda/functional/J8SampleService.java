package cn.thinking.lambda.functional;

import java.util.Arrays;

/**
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

    void methodB();
}
