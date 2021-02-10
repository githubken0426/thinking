package org.thinking.volume21.concurrence.chapter2._04Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskWithResult<V> implements Callable<V> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	/**
	 * 和run()方法区别是：call方法可以抛出异常
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V call() throws Exception {
		return (V) (Thread.currentThread() + ":" + id);
	}

	/**
	 * submit()方法产生Future对象,是异步计算
	 * 
	 * 假设有一个很耗时的返回值需要计算，并且这个返回值不是立刻需要的话，
	 * 那么就可以用一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，
	 * 等到需要这个返回值时，再通过Future得到.
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		List<Future<String>> list = new ArrayList<Future<String>>();
		List<Future<?>> list2 = new ArrayList<Future<?>>();
		for (int i = 0; i < 5; i++) {
			// submit参数也可以为runnable类型，但是其返回值一直为null
			Future<?> fl = exe.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread() + "-" + this.getClass().getName());
				}
			});
			list2.add(fl);
			list.add(exe.submit(new TaskWithResult<String>(i)));
		}
		exe.shutdown();
		for (Future<String> fs : list) {
			if (fs.isDone())
				System.out.println(fs.get());
			TimeUnit.SECONDS.sleep(2);
		}

		for (Future<?> fs : list2) {
			System.out.println("返回值:" + fs.get());
		}
	}
}
