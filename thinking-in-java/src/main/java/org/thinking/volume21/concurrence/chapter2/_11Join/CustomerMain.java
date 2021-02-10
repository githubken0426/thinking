package org.thinking.volume21.concurrence.chapter2._11Join;

public class CustomerMain {

	public static void main(String[] args) throws InterruptedException {
		CustomerThread customer = new CustomerThread(new CustomerJoiner());
		System.out.println(Thread.currentThread() + " start Main!");
		customer.start();
		System.out.println(Thread.currentThread() + " end Main!");
	}
}
