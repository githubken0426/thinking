package org.thinking.volume21.concurrence.chapter2._03Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.thinking.volume21.concurrence.chapter2._01Runnable.LiftOff;

/**
 * newCachedThreadPool()将会为每个任务创建线程 它通常会创建所需要的线程数，回收旧线程时会停止创建新线程 因此它是Executor的首选
 * 在所有线程池中，现有线程有可能的情况下，都会被复用
 * 
 * @author Administrator 2016-1-18 下午04:21:37
 *
 */
public class CashedThreadPool {
	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			exe.execute(new LiftOff());
		}
		/**
		 * 一定要关闭,否则Executors线程会一直运行
		 */
		exe.shutdown();
	}
}
