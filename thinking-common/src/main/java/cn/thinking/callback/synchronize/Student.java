package cn.thinking.callback.synchronize;


import cn.thinking.callback.DoFillJob;
import cn.thinking.callback.SuperCalculator;

/**
 * 回调(CallBack)机制
 *
 * @author Administrator 2016-5-4 上午08:55:57
 */
class Student implements DoFillJob {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void fillBank(double a, double b, double result) {
        System.out.println(name + "回调函数计算结果:" + a + "+" + b + "=" + result);
    }

    public void callBack(double a, double b) {
        System.out.println(name + "问完问题后，等待结果！");
        SuperCalculator.add(a, b, this);
    }
}