package org.thinking.volume21.concurrence.chapter2._08Daemon;

import java.util.concurrent.TimeUnit;

/**
 * 当非后台线程结束时，后台线程也就结束，finally字句并不会执行
 * @author Administrator
 *
 */
public class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			System.out.println("This should always run?");
		}
	}

	/**
	 * finally字句并没有执行
	 * 设置d.setDaemon(false),或者main线程sleep时间超过后台线程，finally字句就会执行
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread d=new Thread(new ADaemon());
		d.setDaemon(true);
		d.start();
		TimeUnit.SECONDS.sleep(1);
	}
}
