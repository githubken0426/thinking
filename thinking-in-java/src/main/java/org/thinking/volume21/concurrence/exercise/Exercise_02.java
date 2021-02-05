package org.thinking.volume21.concurrence.exercise;
import java.util.Arrays;

import org.thinking.TIJ4_code.net.mindview.util.Generator;

/**
 * Fibonacci쳲�����
 * 
 * @author Administrator 2016-1-18 ����02:09:30
 * 
 */
public class Exercise_02 implements Generator<Integer>, Runnable {
	private int count;
	private final int n;

	public Exercise_02(int n) {
		this.n = n;
	}

	private static int fib(int n) {
		if (n < 2)
			return 1;
		int result = fib(n - 2) + fib(n - 1);
//		System.out.println(result);
		return result;
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	@Override
	public void run() {
		Integer[] sequence = new Integer[n];
		for (int i = 0; i < n; i++)
			sequence[i] = next();
		System.out.println("Seq:" + n + ":" + Arrays.toString(sequence));
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(fib(i));
//			new Thread(new Exercise_02(i)).start();
		}
	}
}
