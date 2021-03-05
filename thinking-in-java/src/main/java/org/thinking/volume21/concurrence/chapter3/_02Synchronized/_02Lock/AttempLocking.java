package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttempLocking {
	private Lock lock = new ReentrantLock();

	public void untimed() {
		boolean b = lock.tryLock();
		try {
			System.out.println("tryLock():" + b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (b)
				lock.unlock();
		}
	}

	public void time() {
		boolean b = false;
		try {
			b = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS):" + b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (b)
				lock.unlock();
		}
	}

	public static void main(String[] args) {
		final AttempLocking temp = new AttempLocking();
		temp.untimed();
		temp.time();
		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				temp.lock.lock();
				System.out.println(temp.lock.tryLock() + " acquired");
			}
		}.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//holdsLock()检测一个线程是否拥有锁
		System.out.println("Thread.holdsLock(temp):" + Thread.holdsLock(temp));
		Thread.yield();
		temp.untimed();
		temp.time();

	}
}
