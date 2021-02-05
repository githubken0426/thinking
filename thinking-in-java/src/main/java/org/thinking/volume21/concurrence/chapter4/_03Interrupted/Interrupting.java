package org.thinking.volume21.concurrence.chapter4._03Interrupted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {
	private static ExecutorService exe =Executors.newCachedThreadPool();
	
	/**
	 * ����exe.shutdownNow(),������һ��interrupted()���ø����������߳�
	 * @param r
	 * @throws InterruptedException
	 */
	static void test(Runnable r)throws InterruptedException{
		Future<?> f=exe.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		String className=r.getClass().getName();
		System.out.println(className.substring(className.lastIndexOf(".")+1)+" was raady to Interrupt!");
		/**
		 * cancel()���������ж�ĳ���߳�
		 */
		f.cancel(true);
		System.out.println("Interrupt send to "+className.substring(className.lastIndexOf(".")+1));
	}
	
	/**
	 * �����ж���ͼ��ȡsynchronized����I/O�������߳�
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		test(new SleepBlocked());
//		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(10);
		System.exit(0);
	}
}
