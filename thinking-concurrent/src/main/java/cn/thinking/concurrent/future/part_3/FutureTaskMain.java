package cn.thinking.concurrent.future.part_3;

import java.util.concurrent.*;

/**
 * get()阻塞超时问题
 * @Auther: kun.f.wang
 * @Date: 2/14/2019 09:56
 * @Description:
 */
public class FutureTaskMain {
    public static void main(String[] args) {
        FutureTask<String> futureTask1 = new FutureTask<String>(new MyCallable(1));
        FutureTask<String> futureTask2 = new FutureTask<String>(new MyCallable(2));
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("Done");
                    //shut down executor service
                    executor.shutdown();
                    return;
                }
                if (!futureTask1.isDone()) {
                    //阻塞futureTask1
                    System.out.println("FutureTask1 output = " + futureTask1.get());
                }
                //阻塞，在规定时间内获取不到值get方法会throw new TimeoutException();
                String result = futureTask2.get(500L, TimeUnit.MILLISECONDS);
                System.out.println("Waiting for FutureTask2.result = " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException:" + e.getMessage());
            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println("ExecutionException:" + e.getMessage());
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println("TimeoutException:" + e.getMessage());
            }
        }
    }
}
