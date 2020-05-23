package cn.thinking.concurrent.exectors;

import cn.thinking.concurrent.factory.DeamonThreadFactory;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor extends AbstractExecutorService
 * AbstractExecutorService implements ExecutorService
 * Executors类的底层实现便是ThreadPoolExecutor
 * <p>
 * ExecutorService的submit()和execute()方法区别：
 * submit()有返回值，接收Callable<T>,Runnable,T等参数,通常和Future一起使用
 * execute()没有返回值，只接收Runnable
 *
 * @author Administrator 2016-4-28 上午10:52:44
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    /**
     * 1.corePoolSize:核心线程数,线程池维护线程的最少数量。核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出
     * 2.maximumPoolSize:线程池维护线程的最大数量
     * 3.keepAliveTime:大于核心线程数得最大空闲时间。
     * 4.unit:线程池维护线程所允许的空闲时间的单位
     * 5.workQueue:线程池所使用的缓冲队列
     * 6.handler：线程池对拒绝任务的处理策略
     *
     1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
     2.当线程池等于corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
     3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
     4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
     5.当线程池中大于corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
     6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
     7.当提交任务数超过maxmumPoolSize+workQueue之和时(前面线程都没有执行完),任务会交给RejectedExecutionHandler来处理
     */
//	ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, 
//					long keepAliveTime, TimeUnit unit, 
//					BlockingQueue<Runnable> workQueue, 
//					ThreadFactory threadFactory,
//					RejectedExecutionHandler handler) 

    /**
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters and default thread factory.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code handler} is null
     */

    /**
     * 用SynchronousQueue。可能对于该BlockingQueue有些陌生，简单说：该QUEUE中，
     * 每个插入操作必须等待另一个线程的对应移除操作。
     * 比如，我先添加一个元素，接下来如果继续想尝试添加则会阻塞，直到另一个线程取走一个元素
     */
    public MyThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new DeamonThreadFactory());
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        allowCoreThreadTimeOut(true);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                long keepAliveTime, TimeUnit unit,
                                BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory);
    }

    /**
     * 1：
     * 创建一个可缓存的线程池。如果线程有可用的则重用，否则，创建一个新线程并添加到池中。
     * 终止并从缓存中移除那些已有 60 秒钟未被使用的线程
     *
     * @return 2017-3-21 下午05:00:01
     */
    public static ExecutorService newCahchedPoolThread() {
        return new MyThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new DeamonThreadFactory());
    }

    /**
     * 2：
     * 产生只有一个线程可用来执行任务，
     * 若任务多于一个，任务会被放在一个LinkedBlockingQueue里顺序执行
     *
     * @return 2017-3-21 下午05:01:32
     */
    public static ExecutorService newSingleThreadExecutor() {
        return new MyThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 3：
     * 产生一个大小为nThreads的线程池，
     * 若任务数量大于nThreads，任务会被放在一个LinkedBlockingQueue里顺序执行
     *
     * @param nThreads
     * @return 2017-3-21 下午05:00:33
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 测试函数
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
//		ExecutorService exe = MyThreadPoolExecutor.newCahchedPoolThread();
        /**
         * Executors.callable(Runnable task)方法：将Runnable的任务转化成Callable的任务
         */
		/*try {
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
		}finally{
			exe.shutdown();
		}*/
        testThreadPoolExecutor();
    }

    public static void testThreadPoolExecutor() {
        int corePoolSize = 5, maximumPoolSize = 5;
        long keepAliveTime = 1l;
        TimeUnit unit = TimeUnit.SECONDS;
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ExecutorService exe = new ThreadPoolExecutor
                (corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
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
}
