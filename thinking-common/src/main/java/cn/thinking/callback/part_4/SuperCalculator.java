package cn.thinking.callback.part_4;

/**
 * 计算器类
 *
 * @author Administrator
 */
public class SuperCalculator {
    public static void calcADD(double a, double b, DoJob job) {
        job.fillBank(a, b, a + b);
    }
}
