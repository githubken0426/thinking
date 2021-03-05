package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReenterLockTest {
	public static void main(String[] args) {
//		ReenterLockTest.TestReentrantLock.test();
//		ReenterLockTest.TestReentrantLockInterruptibly.lockInterruptibly();
		new ReenterLockTest.TestReentrantReadWriteLock().reentrantReadWriteLock();
	}

	static class TestReentrantLock {
		private static List<Integer> arrayList = new ArrayList<Integer>();
		private static Lock lock = new ReentrantLock();

		public static void test() {
			new Thread() {
				public void run() {
					insert(Thread.currentThread());
				};
			}.start();

			new Thread() {
				public void run() {
					insert(Thread.currentThread());
				};
			}.start();
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(arrayList);
		}

		public static void insert(Thread thread) {
			lock.lock();
			try {
				System.out.println(thread.getName() + "得到了锁");
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				System.out.println(thread.getName() + "释放了锁");
				lock.unlock();
			}
		}
	}

	static class TestReentrantLockInterruptibly {
		private Lock lock = new ReentrantLock();

		public static void lockInterruptibly() {
			TestReentrantLockInterruptibly test = new TestReentrantLockInterruptibly();
			MyThread thread1 = new MyThread(test);
			MyThread thread2 = new MyThread(test);
			thread1.start();
			thread2.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread2.interrupt();
		}

		public void insert(Thread thread) throws InterruptedException {
			/**
			 * 注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
			 */
			lock.lockInterruptibly();
			try {
				System.out.println(thread.getName() + "得到了锁");
				long startTime = System.currentTimeMillis();
				for (;;) {
					if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
						break;
					//insert operation
				}
			} finally {
				System.out.println(Thread.currentThread().getName() + "执行finally");
				lock.unlock();
				System.out.println(thread.getName() + "释放了锁");
			}
		}
	}

	static class MyThread extends Thread {
		private TestReentrantLockInterruptibly test = null;

		public MyThread(TestReentrantLockInterruptibly test) {
			this.test = test;
		}

		@Override
		public void run() {
			try {
				test.insert(Thread.currentThread());
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
				System.out.println(Thread.currentThread().getName() + "被中断");
			}
		}
	}

	/**
	 * 多个线程同时获取readLock
	 * @author kun.f.wang
	 *
	 */
	static class TestReentrantReadWriteLock {
		private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		public void reentrantReadWriteLock() {
			new Thread() {
				public void run() {
					handlWithLock(Thread.currentThread());
				};
			}.start();

			new Thread() {
				public void run() {
					handlWithLock(Thread.currentThread());
				};
			}.start();

		}

		public void handlWithLock(Thread thread) {
			readWriteLock.readLock().lock();
			try {
				long start = System.currentTimeMillis();
				while (System.currentTimeMillis() - start <= 1) {
					System.out.println(thread.getName() + "正在进行读操作");
				}
				System.out.println(thread.getName() + "读操作完毕");
			} finally {
				readWriteLock.readLock().unlock();
			}
		}
	}
}
