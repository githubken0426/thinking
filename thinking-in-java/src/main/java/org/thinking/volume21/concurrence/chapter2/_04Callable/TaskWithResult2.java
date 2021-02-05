package org.thinking.volume21.concurrence.chapter2._04Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 使用FutureTask
 * 
 * @author ken
 * @param <V>
 * 2017-3-21 下午02:31:47
 */
public class TaskWithResult2<V> implements Callable<V> {
	private String id;
	public TaskWithResult2(String id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V call() throws Exception {
		for (int i = 1; i <= 3; i++) {
			System.out.println("task sleep "+i + "��");
			TimeUnit.SECONDS.sleep(1);
		}
		return (V) (Thread.currentThread() + ":" + id);
	}
	/**
	 * 可以用isDone()方法检查Future是否已经完成; 
	 * 也可直接调用get()，get()会被阻塞直到任务返回结果
	 * 
	 * @param exe
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * 2017-3-21 下午01:57:49
	 */
	static void futureTask(ExecutorService exe){
		try {
//			public class FutureTask implements RunnableFuture 
//			public interface RunnableFuture extends Runnable,Future
			
			FutureTask<String> future = new FutureTask<String>(
					new TaskWithResult2<String>("����ֵ"));
			exe.submit(future);
//			TimeUnit.SECONDS.sleep(4);
			System.out.println("future.isDone()="+future.isDone());
			if(future.isDone())
				System.out.println("future.get():"+future.get());
			System.out.println("method futureTask()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			exe.shutdown();
		}
	}
	
	/**
	 * CompletionService本身自带一个线程安全的线性表，无需用户额外创建。
	 * 它提供了2种方法从线性表中取出结果，
	 * poll()是非阻塞的，若目前无结果，返回一个null，线程继续运行不阻塞。
	 * take()是阻塞的，若当前无结果，则线程阻塞，直到产生一个结果，被取出返回，线程才继续运行。
	 * @param exe
	 * 2017-3-21 下午03:35:41
	 */
	static void completionService(ExecutorService exe){
		try {
			CompletionService<String> cs = new ExecutorCompletionService<String>(exe);
			cs.submit(new TaskWithResult2<String>("我是值"));
//			System.out.println(cs.take().isDone());
			
			System.out.println("future.get():"+cs.take().get());
			System.out.println("method futureTask()");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			exe.shutdown();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
//		futureTask(exe);
		completionService(exe);
	}
}
