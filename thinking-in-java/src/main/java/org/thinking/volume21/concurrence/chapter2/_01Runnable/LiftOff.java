package org.thinking.volume21.concurrence.chapter2._01Runnable;

/**
 * 进程中所包含的一个或多个执行单元称为线程（thread）。
 * 进程还拥有一个私有的虚拟地址空间，该空间仅能被它所包含的线程访问
 */
/**
 * 实际中往往采用实现Runable接口，一方面因为java只支持单继承，继承了Thread类就无法再继续继承其它类，
 * 而且Runable接口需要实现run方法；另一方面通过结果可以看出实现Runable接口才是真正的多线程
 * 
 * @author Administrator 2016-1-18 下午01:48:03 具有可论证的确定性，实际上具有不可确定性
 */

public class LiftOff implements Runnable {
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount++;

	public LiftOff() {
	}

	public LiftOff(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return Thread.currentThread() + ":#id=" + id + ",(" + (countDown > 0 ? countDown : "LiftOff") + "),";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			/**
			 * yield():对线程调度器的一种建议:选择性的， 暂停当前线程，
			 * 由cpu决定执行其他线程(包括自身线程)， 它只能让同优先级的线程有执行的机会
			 */
			Thread.yield();
		}
	}

	public static void main(String[] args) {
		LiftOff lift = new LiftOff();
		/**
		 * 直接调用run()其实是单线程的一种运用 此处为"main"线程
		 */
		lift.run();
		System.out.println("________________________________");
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(new LiftOff());
			t.start();
		}

	}
}
