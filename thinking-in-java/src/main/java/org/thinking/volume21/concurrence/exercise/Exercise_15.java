package org.thinking.volume21.concurrence.exercise;

import java.util.concurrent.TimeUnit;



public class Exercise_15 {
	private Object objG=new Object();
	private Object objH=new Object();
	
	public synchronized void f(){
		for(int i=1;i<=5;i++){
			System.out.println("f()������:"+i+"�Σ�");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Thread.yield();
		}
	}
	
	public void g(){
		synchronized(objG){
			for(int i=1;i<=5;i++){
				System.out.println("g()������:"+i+"�Σ�");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Thread.yield();
			}
		}
	}
	
	public void h(){
		synchronized(objH){
			for(int i=1;i<=5;i++){
				System.out.println("h()������:"+i+"�Σ�");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Thread.yield();
			}
		}
	}
	
	/**
	 * ͨ��sleep���Կ���������������ͬʱ����
	 * @param args
	 */
	public static void main(String[] args){
		final Exercise_15 exe=new Exercise_15();
		new Thread(){
			public void run(){
				exe.f();
			}
		}.start();
		
		new Thread(){
			public void run(){
				exe.g();
			}
		}.start();
		exe.h();
	}
}
