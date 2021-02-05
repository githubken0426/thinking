package org.thinking.volume21.concurrence.chapter4._03Interrupted;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndTryLockAndInterruptibly {
	/**
	 * interrupted()ֻ������������£��Ż�ִ���жϲ��� ����߳�û���������п��ܲ��Ჶ���ж��쳣
	 * 
	 * @author Administrator
	 *
	 */
	static class LockOther implements Runnable {
		private Lock lock = new ReentrantLock();

		@Override
		public void run() {
			String className = Thread.currentThread().getName();
			try {
				lock.lock(); // �ò���lock�Ͳ����ݣ���Ȼ�߳̾�һֱblock��
//				lock.lockInterruptibly();// ���ȿ�����Ӧ�жϣ��������»�ȡ��.�˴�����������catch
//				lock.tryLock(); // tryLock()�����Ϸ��أ��õ�lock�ͷ���true����Ȼ����false��
//				lock.tryLock(2, TimeUnit.SECONDS);// �ò���lock���͵�һ��ʱ�䣬��ʱ����false��

				System.out.println(className.substring(className
						.lastIndexOf(".") + 1) + " Running!");
				TimeUnit.SECONDS.sleep(3);// �˴����������߳�����

				System.out.println(className.substring(className
						.lastIndexOf(".") + 1) + " Finished!");
			} catch (InterruptedException e) {
				System.out.println(className.substring(className
						.lastIndexOf(".") + 1) + " Interrupted!");
			} finally {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new LockOther());
		Thread t2 = new Thread(new LockOther());
		t1.start();
		t2.start();
		t2.interrupt();
	}
}
