package org.thinking.volume21.concurrence.chapter2._14Exception.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorService的submit()和execute()方法区别：
 * submit()有返回值，接收Callable<T>,Runnable,T等参数,通常和Future一起使用
 * execute()没有返回值，只接收Runnable
 * 
 * @author Administrator
 *
 */
public class WorkThreadFactory {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool(new LoggerThreadFactory());
		exe.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("异常开始");
				throw new RuntimeException();
			}
		});

		List<Future<? extends Object>> list = new ArrayList<Future<? extends Object>>();
		list.add(exe.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Future捕获异常");
				throw new RuntimeException();
			}
		}));
		exe.shutdown();//执行完毕需要马上关闭，否则future捕获异常后，无法关闭.
		for (Future<? extends Object> fs : list) {
			System.out.println(fs.get());
		}

	}
}
