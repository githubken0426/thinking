package org.thinking.volume21.concurrence.chapter5._01;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax On");
				TimeUnit.MICROSECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
//			e.printStackTrace();
			System.out.println("WaxOn Interrupted"+e.toString());
		}finally{
			System.out.println("Ending Wax On Task!");
		}
	}

}
