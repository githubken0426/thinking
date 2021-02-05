package org.thinking.volume21.concurrence.chapter4._03Interrupted.mutex_block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedMutex {
	private Lock lock=new ReentrantLock();
	
	public BlockedMutex(){
		/**
		 * ����֤����ReentrantLock���������̻��һ��������������
		 * f()�����е���Ҳһ��
		 */
		lock.lock();
	}
	
	public void f(){
		try {
			/**
			 * lock����Ĭ�ϴ������ж�����һ����⵽�ж�״̬�����жϵ�ǰ�̣߳�
			 * ����Lock������������ʱ���ǲ���Ӧ�жϵġ� 
			 * lock��ȡ�������У��������жϣ��ڳɹ���ȡ��֮���ٸ����жϱ�ʶ�����ж�
			 * 
			 * ��lockInterruptibly()��ֱ���׳��ж��쳣�����ϲ��������ȥ�����ж�
			 */
			lock.lockInterruptibly();
//			lock.lock();
			System.out.println("Lock by f()");
		} catch (Exception e) {
			System.out.println("InterruptedException Interrupted from f()");
		}
	}
	
	static class Block2 implements Runnable{
		BlockedMutex b=new BlockedMutex();
		@Override
		public void run() {
			System.out.println("Wwait for BlockedMutes");
			b.f();
			System.out.println("break out of blocked call");
		}
		
	}
	
	/**
	 * interrupt()�������Դ�ϱ������������ĵ���
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new Block2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("ִ�� t.interrupt()");
		t.interrupt();
	}
}
