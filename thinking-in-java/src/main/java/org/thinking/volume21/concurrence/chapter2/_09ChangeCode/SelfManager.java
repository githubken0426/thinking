package org.thinking.volume21.concurrence.chapter2._09ChangeCode;

public class SelfManager implements Runnable {
	private int countDown = 5;
	private Thread t = new Thread(this);

	/**
	 * 构造器中启用线程，可能会有问题: 
	 * 因为另一个线程可能会在构造器结束之前开始执行，这意味着该任务可能会访问不稳定状态的对象，这是优先选用Executor的原因
	 */
	public SelfManager() {
		t.start();
	}

	public String toString() {
		return Thread.currentThread() + "(" + countDown + "),";
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new SelfManager();
	}
}
