package org.thinking.volume21.concurrence.chapter2._11Join;

/**
 * 线程阻塞:指一个线程在执行过程中暂停，以等待某个条件的触发。
 * 
 * @author Administrator
 *
 */
public class JoinMain {
	public static void main(String[] args) {
		Sleeper sleeper = new Sleeper("Sleepy", 6);
		Joiner joiner = new Joiner("Joiner", sleeper);


		/**
		 * Joiner调用interrupt()方法，等到调用join()对象执行完毕，Joiner才会被执行中断异常
		 * Sleeper调用interrupt()方法, 可以看到只要调用interrupted()方法，均会执行中断异常
		 */
//		sleeper.interrupt();
		joiner.interrupt();

//		Sleeper sleepy1=new Sleeper("Sleepy1",10);
//		Joiner dopey=new Joiner("Joiner sleepy1",sleepy1);
//		dopey.interrupt();
	}
}
