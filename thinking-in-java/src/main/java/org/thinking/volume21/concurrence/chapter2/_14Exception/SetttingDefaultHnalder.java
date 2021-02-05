package org.thinking.volume21.concurrence.chapter2._14Exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread.setDefaultUncaughtExceptionHandler()
 * 系统会先检查线程是否有专有的uncaughtException()方法， 如果没有就在调用defaultUncaughtExceptionHandler
 * 
 * @author Administrator 2016-4-29 上午10:26:17
 *
 */
public class SetttingDefaultHnalder {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("caught exception:" + e);
			}
		});
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new ExceptionThread());
		exe.shutdown();
	}
}
