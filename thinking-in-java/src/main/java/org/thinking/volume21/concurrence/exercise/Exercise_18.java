package org.thinking.volume21.concurrence.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Exercise_18 {
	static class NonTask{
		static void longMthod() throws InterruptedException{
			System.out.println("��˯5��");
			TimeUnit.SECONDS.sleep(5);
		}
	}
	
	class Task implements Runnable{

		@Override
		public void run() {
			try {
				NonTask.longMthod();
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}finally{
				//�����쳣����
			}
		}
	}
	class TaskFactory implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			Thread t=new Thread(r);
			return t;
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Exercise_18 e=new Exercise_18();
		ExecutorService exe=Executors.newCachedThreadPool(e.new TaskFactory());
		exe.execute(e.new Task());
		TimeUnit.SECONDS.sleep(1);
		exe.shutdownNow();
	}
}
