package org.thinking.volume21.concurrence.chapter3._01WrongVisit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator g, int id) {
		this.id = id;
		this.generator = g;
	}

	@Override
	public void run() {
		while (!generator.isCancel()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cacel();
			}
		}
	}

	public static void test(IntGenerator g, int count) {
		System.out.println("Press Contorl-C to exit!");
		ExecutorService exe = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++)
			exe.execute(new EvenChecker(g, i));
		exe.shutdown();
	}

	public static void test(IntGenerator g) {
		test(g, 10);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test(new EvenGenerator(), 10);
	}
}
