package org.thinking.volume21.concurrence.chapter2._14Exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		System.out.println("Created " + t + " by {" + this.getClass().getSimpleName() + "}");
		/**
		 * setUncaughtExceptionHandler()方法传入一个Thread.UncaughtExceptionHandler对象
		 * 只有在抛出异常时候，才会执行
		 */
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("UncaughtExceptionHandler Throwable:[" + e + "] t.getName():[" + t.getName() + "]");
			}
		});
		System.out.println("HandlerThreadFactory ：" + t.getUncaughtExceptionHandler());
		return t;
	}

	public String toString() {
		return this.getClass().getName();
	}
}
