package org.thinking.volume21.concurrence.chapter2._04Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * 使用FutureTask
 * 
 * @author ken
 * @param <V>
 * 2017-3-21 下午02:31:47
 */
public class TaskWithResult3<V> implements Callable<V> {
	private String id;
	public TaskWithResult3() {}
	public TaskWithResult3(String id) {
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V call() throws Exception {
		return (V) (Thread.currentThread() + ":" + id);
	}
	/**
	 * CompletionService本身自带一个线程安全的线性表，无需用户额外创建。
	 * 它提供了2种方法从线性表中取出结果，
	 * poll()是非阻塞的，若目前无结果，返回一个null，线程继续运行不阻塞。
	 * take()是阻塞的，若当前无结果，则线程阻塞，直到产生一个结果，
	 * 被取出后返回，线程才继续运行。
	 * @param exe
	 * 2017-3-21 下午03:35:41
	 */
	static void completionService(ExecutorService exe){
		try {
			System.out.println("method completionService()");
			CompletionService<String> cs = new ExecutorCompletionService<String>(exe);
			
			for(int i=0;i<3;i++){
				cs.submit(new TaskWithResult3<String>(i+""));
				Future<String> future = cs.poll();
				if(future ==null)
					System.out.println("任务尚未完成！");
				else
					System.out.println("future.get():"+future.get());
				TimeUnit.SECONDS.sleep(3);
			}
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
		completionService(exe);
	}
}
