package org.thinking.volume21.concurrence.chapter2._14Exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		System.out.println("**run() by " + Thread.currentThread());
		System.out.println("**run() en:" + Thread.currentThread().getUncaughtExceptionHandler());
		/**
		 * 内部类的实现： 抛出异常，需要在HandlerThreadFactory的setUncaughtExceptionHandler()方法中捕获此异常，
		 * 所以还会执行HandlerThreadFactory中的run()。
		 */
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exe.execute(new ExceptionThread2());
		exe.shutdown();
	}
}
