package org.thinking.volume21.concurrence.chapter2._08Daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor extends AbstractExecutorService AbstractExecutorService
 * implements ExecutorService Executors类的底层实现便是ThreadPoolExecutor
 * 
 * @author Administrator 2016-4-28 上午10:52:44
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

	public MyThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	/**
	 * 返回DaemonThreadPoolExecutor的构造
	 * 继承自ThreadPoolExecutor，也相当返回ThreadPoolExecutor的构造
	 * 产生一个ExecutorService对象,带有一个线程池，线程池的大小会根据需要调整，线程执行完任务后返回线程池，供执行下一次任务使用
	 * 
	 * @return
	 */
	public static ExecutorService newCahchedPoolThread() {
		return new MyThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new DaemonThreadFactory());
	}

	/**
	 * 产生一个ExecutorService对象,只有一个线程可用来执行任务，若任务多于一个，任务将按先后顺序执行。
	 * 
	 * @return
	 */
	public static ExecutorService newSingleThreadExecutor() {
		return new MyThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	/**
	 *  产生一个ExecutorService对象,带有一个大小为poolSize的线程池，若任务数量大于poolSize，任务会被放在一个queue里顺序执行
	 * @param nThreads
	 * @return
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * newSingleThreadScheduledExecutor：产生一个ScheduledExecutorService对象，
	 * 这个对象的线程池大小为1，若任务多于一个，任务将按先后顺序执行。 newScheduledThreadPool(int poolSize ):
	 * 产生一个ScheduledExecutorService对象，
	 * 这个对象的线程池大小为poolSize，若任务数量大于poolSize，任务会在一个queue里等待执行
	 */
	/**
	 * 测试函数
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		ScheduledExecutorService scheduled=Executors.newSingleThreadScheduledExecutor();
		ExecutorService exe = MyThreadPoolExecutor.newCahchedPoolThread();
		/**
		 * Executors.callable(Runnable task)方法：将Runnable的任务转化成Callable的任务
		 */
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

		// exe.execute(new Runnable() {
		// @Override
		// public void run() {
		// for (int i = 0; i < 10; i++)
		// System.out.print(i);
		// System.out.println("\nsleep 3 seconds");
		// }
		// });
		// TimeUnit.SECONDS.sleep(3);
		// Thread t = new Thread() {
		// @Override
		// public void run() {
		// while (true)
		// System.out.println("deamon");
		// }
		// };
		// t.setDaemon(true);
		// exe.execute(t);

		exe.shutdown();

		for (Future<Object> f : list) {
			System.out.println(f);
		}
	}
}
