package cn.thinking.callback.part_4;

import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) {
        final Student stu = new Student("小明");
        final Seller seller = new Seller("阿婆");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始为阿婆计算....");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("耗时10秒");
                    seller.callHelp(15693.00, 25863.0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("开始为小明计算....");
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("耗时20秒");
                    stu.callHelp(565.00, 3406.03);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
