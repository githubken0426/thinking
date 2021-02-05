package org.thinking.volume21.concurrence.chapter5._01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car=new Car();
		ExecutorService exe=Executors.newCachedThreadPool();
		
		exe.execute(new WaxOn(car));
		exe.execute(new WaxOff(car));
		
		TimeUnit.SECONDS.sleep(5);
		exe.shutdownNow();
	}
}
