package cn.thinking.callback.part_3;

/**
 * 计算器
 *
 * @author Administrator
 */
public class SuperCalculator {
    public static void calcADD(double a, double b, Student stu) {
        stu.fillBank(a, b, a + b);
    }
}
