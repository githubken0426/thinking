package org.thinking.volume21.concurrence.chapter2._08Daemon;

import java.util.concurrent.TimeUnit;

public class Daemon implements Runnable {
	private static int id=1;
	private Thread [] t=new Thread[10];
	
	/**
	 * 虽然run()方法进入死循环，因为是后台线程，非后台线程结束时，也就杀死了后台线程，死循环结束
	 */
	@Override
	public void run() {
		for(int i=0;i<t.length;i++){
			t[i]=new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DeamonSpawn_"+i+":Started!");
		}
		try {
			/*
			 * 当main线程的sleep时间小于后台线程的sleep时间时候
			 * 也就是main线程死亡时候就会杀死后台线程，此后面代码有可能不会被执行
			 */
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int z=0;z<t.length;z++)
			System.out.println(t[z].getName()+ ",t["+z+"].isDaemon():"+t[z].isDaemon()+",");
		while(true)
			Thread.yield();
	}

	public class DaemonSpawn implements Runnable {
		@Override
		public void run() {
			System.out.println("test"+id++);
			while(true){
				Thread.yield();
			}
		}
	}

	/**
	 * 注释掉d.setDaemon(true)，程序就会进入死循环
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread d=new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon():"+d.isDaemon()+",我要沉睡三秒");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("end!");
	}
}