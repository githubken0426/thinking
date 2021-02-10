package org.thinking.volume21.concurrence.chapter2._05Sleep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.thinking.volume21.concurrence.chapter2._01Runnable.LiftOff;

public class SleepingTask extends LiftOff {
	
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			try {
				/**
				 * 异常不能跨线程传播到main，所以只能在本地捕获
				 */
				// old
				Thread.sleep(1000);
				// new
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exe.execute(new SleepingTask());
		exe.shutdown();
	}
}
