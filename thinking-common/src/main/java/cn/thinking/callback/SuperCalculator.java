package cn.thinking.callback;

import java.util.concurrent.TimeUnit;


public class SuperCalculator {
    /**
     * 此处进行了函数的回调
     *
     * @param a
     * @param b
     */
    public static void add(double a, double b, DoFillJob fill) {
        try {
            System.out.println("计算器计算耗时10秒！");
            TimeUnit.SECONDS.sleep(10);
            fill.fillBank(a, b, a + b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
