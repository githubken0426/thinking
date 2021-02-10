package org.thinking.volume21.concurrence.chapter2._14Exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		try {
			ExecutorService exe=Executors.newCachedThreadPool();
			exe.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("Cathch Exception");
			e.printStackTrace();
		}
	}
}
