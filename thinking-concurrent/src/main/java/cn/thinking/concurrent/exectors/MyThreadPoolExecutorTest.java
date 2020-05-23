package cn.thinking.concurrent.exectors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor extends AbstractExecutorService AbstractExecutorService
 * implements ExecutorService Executors类的底层实现便是ThreadPoolExecutor
 * <p>
 * ExecutorService的submit()和execute()方法区别：
 * submit()有返回值，接收Callable<T>,Runnable,T等参数,通常和Future一起使用
 * execute()没有返回值，只接收Runnable
 *
 * @author Administrator 2016-4-28 上午10:52:44
 *
 */
public class MyThreadPoolExecutorTest {
    public static void main(String[] args) {
        MyThreadPoolExecutorTest test = new MyThreadPoolExecutorTest();
        // 抛弃旧的任务
        RejectedExecutionHandler hanlder;
        // 抛弃当前任务
        hanlder = new ThreadPoolExecutor.DiscardPolicy();
        // 抛出java.util.concurrent.RejectedExecutionException异常
        // hanlder=new ThreadPoolExecutor.AbortPolicy();
        // 重试添加当前的任务
        hanlder = new ThreadPoolExecutor.CallerRunsPolicy();
//		 hanlder=new ThreadPoolExecutor.DiscardOldestPolicy();
//		test.testThreadPoolExecutor2(hanlder);
        test.testThreadPoolExecutor3(hanlder);
    }

    public void testThreadPoolExecutor2(RejectedExecutionHandler handler) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 2, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                handler);

        for (int i = 1; i <= 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            // 产生一个任务，并将其加入到线程池
            String task = "task@ " + i;
            System.out.println("put " + task + ",队列长度：" + getQueueSize(threadPool.getQueue()));
            while (getQueueSize(threadPool.getQueue()) >= 2) {
                System.out.println("队列已满，等3秒再添加任务");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final int a = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + " index:" + a);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }

    class ThreadPoolTask implements Runnable, Serializable {
        private static final long serialVersionUID = 0;
        // 保存任务所需要的数据
        private Object threadPoolTaskData;

        ThreadPoolTask(Object tasks) {
            this.threadPoolTaskData = tasks;
        }

        public void run() {
            // 处理一个任务
            System.out.println(Thread.currentThread() + "start "
                    + threadPoolTaskData);
            try {
                // 便于观察，等待一段时间
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadPoolTaskData = null;
        }

        public Object getTask() {
            return this.threadPoolTaskData;
        }
    }

    public void testThreadPoolExecutor3(RejectedExecutionHandler handler) {
        int queueDeep = 2;
        /*
         * 创建线程池，最小线程数为2，最大线程数为4，线程池维护线程的空闲时间为3秒，
         * 使用队列深度为4的有界队列，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，
         * 然后重试执行程序（如果再次失败，则重复此过程），里面已经根据队列深度对任务加载进行了控制。
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 2, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueDeep),
                handler);
        // 向线程池中添加 10 个任务
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("队列长度：" + getQueueSize(threadPool.getQueue()));
            while (getQueueSize(threadPool.getQueue()) >= queueDeep) {
                System.out.println("队列已满，等3秒再添加任务");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final int a = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + " index:" + a);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }

    private synchronized int getQueueSize(Queue<?> queue) {
        return queue.size();
    }

    /**
     * 2017-3-29 下午03:55:46
     */
    public static void testThreadPoolExecutor() {
        int corePoolSize = 5, maximumPoolSize = 5;
        long keepAliveTime = 1l;
        TimeUnit unit = TimeUnit.SECONDS;
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
        ExecutorService exe = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit, workQueue);
        for (int i = 0; i < 5; i++) {
            final int a = i;
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName(a + "");
                    System.out.println(Thread.currentThread() + "_" + a);
                }
            });
        }
        exe.shutdown();
    }

    /**
     * Executors.callable(Runnable task)方法：将Runnable的任务转化成Callable的任务
     * <p>
     * 2017-3-29 下午03:55:34
     */
    public static void testRunnableChangeToCallable() {
        ExecutorService exe = MyThreadPoolExecutor.newCahchedPoolThread();
        try {
            Callable<Object> cll = Executors.callable(new Runnable() {
                @Override
                public void run() {
                    System.out.println("this:" + this);
                }
            });
            List<Future<Object>> list = new ArrayList<Future<Object>>();
            for (int i = 0; i < 5; i++) {
                list.add(exe.submit(cll));
            }
            for (Future<Object> f : list) {
                System.out.println(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exe.shutdown();
        }
    }
}
