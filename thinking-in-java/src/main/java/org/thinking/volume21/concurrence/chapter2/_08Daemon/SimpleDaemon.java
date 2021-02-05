package org.thinking.volume21.concurrence.chapter2._08Daemon;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程(后台线程)
 * 
 * @author Administrator 2016-1-19 下午03:33:19
 * 
 */
public class SimpleDaemon implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread()+":"+this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 后台线程会一直运行，直到非后台线程结束
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<10;i++){
			Thread thread=new Thread(new SimpleDaemon());
			thread.setDaemon(true);//必须在start()之前调用
			thread.start();
		}
		System.out.println("All daemon start!");
		TimeUnit.SECONDS.sleep(5);
	}
}
