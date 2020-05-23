package cn.thinking.concurrent.future.part_1;

import java.util.concurrent.*;

/**
 * @Auther: kun.f.wang
 * @Date: 2/14/2019 11:30
 * @Description:
 */
public class CallableFutureTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test1();
        test2();
    }

    public static void test1() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new Task());
        executor.shutdown();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("主线程在执行任务");
        System.out.println("task运行结果" + result.get());
        System.out.println("所有任务执行完毕");
    }
    
    public static void test2() throws InterruptedException, ExecutionException {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        executor.submit(futureTask);
        executor.shutdown();
        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        Thread thread = new Thread(futureTask);
        thread.start();
        */
        TimeUnit.SECONDS.sleep(2);
        System.out.println("主线程在执行任务");
        System.out.println("task运行结果" + futureTask.get());
        System.out.println("所有任务执行完毕");
    }

}