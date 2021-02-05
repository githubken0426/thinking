package org.thinking.volume21.concurrence.chapter2._09ChangeCode;

public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable1("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}
}
