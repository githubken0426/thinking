package org.thinking.volume21.concurrence.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Exercise_20 {
	static class LiftOff implements Runnable {
		protected int countDown=5000;
		private static int taskCount=0;
		private final int id=taskCount++;
		
		public LiftOff(){}
		
		public LiftOff(int countDown){
			this.countDown=countDown;
		}
		public String status(){
			return Thread.currentThread()+":#"+ id+"("+(countDown>0?countDown:"LiftOff")+"),";
		}
		@Override
		public void run() {
			while (countDown-->0) {
				if(Thread.currentThread().isInterrupted()){
					System.out.println("Interrputed by "+id);
					return;
				}
				System.out.println(status());
				/**
				 * yield():���̵߳�������һ�ֽ���:ѡ���Եģ�
				 * ��ͣ��ǰ�̣߳���cpu����ִ�������߳�(���������߳�)��
				 * ��ֻ����ͬ���ȼ����߳���ִ�еĻ���
				 */
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exe=Executors.newCachedThreadPool();
		for(int i =0;i<5;i++){
			exe.execute(new LiftOff());
		}
		Thread.yield();
		exe.shutdownNow();
	}
}
