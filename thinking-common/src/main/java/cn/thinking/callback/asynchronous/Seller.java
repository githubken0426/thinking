package cn.thinking.callback.asynchronous;


import cn.thinking.callback.DoFillJob;
import cn.thinking.callback.SuperCalculator;

public class Seller implements DoFillJob {
    private String name;

    public Seller(String name) {
        this.name = name;
    }

    @Override
    public void fillBank(double a, double b, double result) {
        System.out.println(name + "调用回调函数计算结果:" + a + "+" + b + "=" + result);
    }

    public void callBack(final double a, final double b) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new SuperCalculator().add(a, b, new Seller(name));
            }
        }).start();
        System.out.println(name + "问完问题后，就继续卖东西了！");
    }
}
