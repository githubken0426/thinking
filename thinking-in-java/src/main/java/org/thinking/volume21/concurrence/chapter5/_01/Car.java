package org.thinking.volume21.concurrence.chapter5._01;

public class Car {
	private boolean waxOn = false;

	public synchronized void waxed() {
		waxOn = true;
		this.notifyAll();
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true)
			wait();
	}

	public synchronized void buffed() {
		waxOn = false;
		this.notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();
		}
	}
}
