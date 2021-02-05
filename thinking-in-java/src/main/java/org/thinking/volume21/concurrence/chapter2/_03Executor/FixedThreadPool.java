package org.thinking.volume21.concurrence.chapter2._03Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.thinking.volume21.concurrence.chapter2._01Runnable.LiftOff;

/**
 * newFixedThreadPool指定线程数量 在所有线程池中，现有线程有可能的情况下，都会被复用
 * 
 * @author Administrator 2016-1-18 下午04:04:48
 *
 */
public class FixedThreadPool {
	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			exe.execute(new LiftOff());
		}
		exe.shutdown();
	}
}
