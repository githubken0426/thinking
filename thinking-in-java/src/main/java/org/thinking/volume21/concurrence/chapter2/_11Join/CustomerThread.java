package org.thinking.volume21.concurrence.chapter2._11Join;

public class CustomerThread extends Thread {
	
	public CustomerThread() {
		super("[CustomThread]");
	}

	public void run() {
		System.out.println(Thread.currentThread() + " start.");
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread() + " loop at " + i);
				Thread.sleep(1000);
			}
			System.out.println(Thread.currentThread() + " end CustomerThread.");
		} catch (Exception e) {
			System.out.println("Exception from " + Thread.currentThread() + ".run");
		}
	}
}
