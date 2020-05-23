package cn.thinking.callback.asynchronous;


import cn.thinking.callback.DoFillJob;
import cn.thinking.callback.SuperCalculator;

/**
 * 异步回调
 *
 * @author Administrator
 * 2016-5-4 上午09:47:03
 */
class Student implements DoFillJob {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void fillBank(double a, double b, double result) {
        System.out.println(name + "调用回调函数,计算结果:" + a + "+" + b + "=" + result);
    }

    public void callBack(final double a, final double b) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                new SuperCalculator().add(a, b, new Student(name));
            }
        }).start();
        System.out.println(name + "问完问题后，就出去玩了！");
    }
}
