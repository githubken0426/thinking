package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * wait和notify都是Object中的方法,在调用这两个方法前必须先获得锁对象，但是park不需要获取某个对象的锁就可以锁住线程。
 * notify只能随机选择一个线程唤醒，无法唤醒指定的线程，unpark却可以唤醒一个指定的线程。
 * 
 * @author kun.f.wang
 *
 */
public class LockSupportTest {
	public static void main(String[] args) {
//		testThread1();
		testThread2();
	}

	static void testThread1() {
		MyThread1 t1 = new MyThread1();
		t1.start();
		System.out.println("MyThread1 park");
		t1.interrupt();
		System.out.println("test1 end");
	}

	static void testThread2() {
		MyThread2 t2 = new MyThread2();
		t2.start();
		System.out.println("MyThread2 park");
		LockSupport.unpark(t2);
		System.out.println("test2 end");
	}
}

/**
 * Interrupt不会抛出异常
 * 
 * @author kun.f.wang
 *
 */
class MyThread1 extends Thread {
	public void run() {
		System.out.println(Thread.currentThread().getName());
		LockSupport.park();
		System.out.println(Thread.currentThread().isInterrupted());
	}
}

class MyThread2 extends Thread {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
		LockSupport.park();
		System.out.println(Thread.currentThread().isInterrupted());
	}
}