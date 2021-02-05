package org.thinking.volume21.concurrence.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Exercise_09 {
	static class SimplePriority2 implements Runnable {
		private int countDown = 5;
		private volatile double d;// no optimization(���������Ż�)

		public String toString() {
			return Thread.currentThread() + ":" + countDown;
		}

		@Override
		public void run() {
			for (;;) { // An expensive, interruptable operation,(�ķ��ڴ���жϵı��ʽ)����ѭ��
				for (int i = 0; i < 1000000; i++) {
					d += (Math.PI + Math.E) / (double) i;
					if (i % 100 == 0)
						Thread.yield();
				}
				System.out.println(this);
				if (--countDown == 0)
					return;
			}
		}
	}

	static class SimplePriorityFactory implements ThreadFactory {
		private int priority;

		public SimplePriorityFactory(int priority) {
			this.priority = priority;
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setPriority(priority);
			return t;
		}
	}

	public static void main(String[] args) {
		ExecutorService exe = Executors.newCachedThreadPool(new SimplePriorityFactory(Thread.MIN_PRIORITY));
		for (int i = 0; i < 5; i++)
			exe.execute(new SimplePriority2());
		Thread.yield();
		exe.shutdown();

		exe = Executors.newCachedThreadPool(new SimplePriorityFactory(Thread.MAX_PRIORITY));
		exe.execute(new SimplePriority2());
		Thread.yield();
		exe.shutdown();
	}
}
