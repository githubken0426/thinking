package org.thinking.volume21.concurrence.exercise;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise_14 {
	static class Thread1 implements Runnable{
		
		private static volatile int count=0;
		@Override
		public void run() {
			while(true){
				
				new Timer().schedule(new TimerTask(){
					@Override
					public void run() {
						System.out.println(Thread.currentThread()+"��"+(++count)+"������");
					}
				}, 100);
				if(count>10){//count��û�пɼ��ԡ��˴��е�����
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exe=Executors.newCachedThreadPool();
		exe.execute(new Thread1());
		exe.shutdown();
//		new Thread(new Thread1()).start();
	}
	
}
