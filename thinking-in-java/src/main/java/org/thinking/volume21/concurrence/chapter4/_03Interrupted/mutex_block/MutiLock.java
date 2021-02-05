package org.thinking.volume21.concurrence.chapter4._03Interrupted.mutex_block;

public class MutiLock {
	public synchronized void f1(int count){
		if(count-->0){
			System.out.println("f1() calling f2() with "+count);
			f2(count);
		}
	}
	
	public synchronized void f2(int count){
		if(count-->0){
			System.out.println("f2() calling f1() with count "+count);
			f1(count);
		}
	}
	
	/**
	 * һ�������ܹ�����ͬһ�������е�����synchronized����
	 * @param args
	 */
	public static void main(String[] args) {
		final MutiLock mutiLock=new MutiLock();
		new Thread(){
			public void run(){
				mutiLock.f1(10);
			}
		}.start();
	}
}
