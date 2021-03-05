package org.thinking.volume21.concurrence.chapter2._03Executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor extends AbstractExecutorService AbstractExecutorService implements ExecutorService 
 * 
 * Executors类的底层实现便是ThreadPoolExecutor
 * ExecutorService的submit()和execute()方法区别：
 * submit()有返回值，接收Callable<T>,Runnable,T等参数,通常和Future一起使用
 * execute()没有返回值，只接收Runnable
 * 
 * @author Administrator 2016-4-28 上午10:52:44
 * 
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

	/**
	 * 1.corePoolSize:核心线程数,线程池维护线程的最少数量.在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，
	 * 除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法----即在没有任务到来之前就创建corePoolSize个线程。
	 * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中； 
	 * 
	 * 2.maximumPoolSize:线程池维护线程的最大数量
	 * 
	 * 3.keepAliveTime:表示线程没有任务执行时最多保持多久时间会终止,直到线程池中的线程数不超过corePoolSize。
	 * 默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用。
	 * 核心线程在allowCoreThreadTimeout被设置为true时会超时退出，直到线程池中的线程数为0。
	 * 
	 * 4.unit:线程池维护线程所允许的空闲时间的单位
	 * 
	 * 5.workQueue:线程池所使用的缓冲队列。LinkedBlockingQueue、SynchronousQueue、 ArrayBlockingQueue、PriorityBlockingQueue。
	 * 
	 * 6.handler：线程池对拒绝任务的处理策略 
	 * 		ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
	 * 		ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
	 * 		ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程） 
	 * 		ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
	 * 
	   *      如果此时线程池中的数量小于corePoolSize， 即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务
	 * 	如果此时线程池中的数量等于 corePoolSize， 但是缓冲队列 workQueue未满，那么任务被放入缓冲队列. 
	 * 	如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务.
	 * 	如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，则已超出线程池的处理能力，
	 * 
	 */
//	ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, 
//					long keepAliveTime, TimeUnit unit, 
//					BlockingQueue<Runnable> workQueue, 
//					ThreadFactory threadFactory,
//					RejectedExecutionHandler handler) 

	/**
	 * SynchronousQueue队列的特别之处在于它内部没有容器.
	 * 一个生产线程，当它put的时候，如果当前没有线程执行take，此生产线程必须阻塞，等待一个消费线程调用take操作，take操作将会唤醒该生产线程，
	 * 同时消费线程会获取生产线程的产品（即数据传递），这样的一个过程称为一次配对过程(当然也可以先take后put,原理是一样的)
	 */
	public MyThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DeamonThreadFactory());
	}

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		allowCoreThreadTimeOut(true);
	}

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	/**
	 * 【强制】线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，
	 * 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 
	 * 
	 * 说明：Executors各个方法的弊端：
	 * 1）newFixedThreadPool和newSingleThreadExecutor:主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
	 * 2）newCachedThreadPool和newScheduledThreadPool:主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
	 * 
	 * @author kun.f.wang
	 */
	static class MyExecutors {
		/**
		 * 当一个任务提交时，corePoolSize为0不创建核心线程，SynchronousQueue是一个不存储元素的队列，可以理解为队里永远是满的，因此最终会创建非核心线程来执行任务。
		 * 对于非核心线程空闲60s时将被回收。因为Integer.MAX_VALUE非常大，可以认为是可以无限创建线程的，在资源有限的情况下容易引起OOM异常
		 * 
		 * @author Administrator 2016-1-18 下午04:17:42
		 */
		public static ExecutorService newCachedThreadPool() {
			return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
					new SynchronousQueue<Runnable>());
		}

		/**
		 * 首先会创建一个核心线程来执行任务，如果超过核心线程的数量，将会放入队列中，LinkedBlockingQueue是长度为Integer.MAX_VALUE的队列，可以认为是无界队列，
		 * 因此往队列中可以插入无限多的任务，在资源有限的时候容易引起OOM异常;
		 * 同时因为无界队列，maximumPoolSize和keepAliveTime参数将无效，压根就不会创建非核心线程.
		 * 
		 * @author Administrator 2016-1-18 下午04:04:48
		 *
		 */
		public static ExecutorService newSingleThreadExecutor() {
			new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
			return Executors.newSingleThreadExecutor();
		}

		/**
		 * 同newSingleThreadExecutor,因无界队列容易引起OOM异常.
		 * 
		 * newSingleThreadExecutor采用FIFO，保证线程执行顺序，先提交的任务先执行，而newFixedThreadPool(1)不保证。
		 * newSingleThreadExecutor方法中，当线程执行出现异常时，它会重新创建一个线程替换之前的线程继续执行，而newFixedThreadPool(1)不行。
		 * 
		 * @author Administrator 2016-1-18 下午04:21:37
		 *
		 */
		public static ExecutorService newFixedThreadPool(int nThreads) {
			return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
					new LinkedBlockingQueue<Runnable>());
		}
	}


	public static void testThreadPoolExecutor() {
		int corePoolSize = 1, maximumPoolSize = 2;
		long keepAliveTime = 1l;
		TimeUnit unit = TimeUnit.SECONDS;
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
		ExecutorService exe = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				Executors.defaultThreadFactory(), new MyRejectedExecutionHandler());
		for (int i = 0; i < 8; i++) {
			final int a = i;
			exe.execute(new Runnable() {
				@Override
				public void run() {
					Thread.currentThread().setName(a + ".");
					System.out.println(Thread.currentThread() + "_" + a);
				}
			});
		}
		exe.shutdown();
	}
	

	public static void main(String[] args) {
		testThreadPoolExecutor();
	}
}
