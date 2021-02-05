package org.thinking.volume21.concurrence.chapter3._02Synchronized._07Local;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {

	/**
	 * increment()��get()������synchronized��ThreadLocal�������̼߳�ľ���
	 */
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);

		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exe.execute(new Accessor(i));
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exe.shutdown();
	}
}
