package org.thinking.volume21.concurrence.chapter2._11Join;

import java.util.concurrent.TimeUnit;

public class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		this.duration = sleepTime;
		start();

	}

	@Override
	public void run() {
		try {
			System.out.println(getName() + "将沉睡：" + duration + "秒");
			TimeUnit.SECONDS.sleep(duration);
//			sleep(duration);
		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println(getName()+"被中断了，所以不再沉睡！");
			System.out.println(getName() + " was interrupted.isInterrupted():" + isInterrupted());
			return;
		} finally {
			System.out.println(getName() + " has awakened! Sleeper执行完毕！");
		}
	}

}
