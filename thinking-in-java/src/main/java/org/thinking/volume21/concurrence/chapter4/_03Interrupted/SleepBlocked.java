package org.thinking.volume21.concurrence.chapter4._03Interrupted;

import java.util.concurrent.TimeUnit;

public class SleepBlocked implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException SleepBlocked�ж�catch��!");
		}
		System.out.println("Exting SleepBlocked.run() ");
	}
	

}
