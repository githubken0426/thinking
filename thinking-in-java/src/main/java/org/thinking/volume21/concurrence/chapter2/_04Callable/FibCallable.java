package org.thinking.volume21.concurrence.chapter2._04Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.thinking.TIJ4_code.net.mindview.util.Generator;

public class FibCallable<V> implements Generator<Integer>, Callable<Integer> {
	private int count;
	private int index;

	public FibCallable(int index) {
		this.index = index;
	}

	public int fib(int n) {
		if (n < 2)
			return 1;
		return fib(n - 2) + fib(n - 1);
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < index; i++) {
			sum += next();
		}
		return sum;
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService exe = Executors.newCachedThreadPool();
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		for (int i = 0; i < 10; i++) {
			list.add(exe.submit(new FibCallable<Object>(i)));
		}
		exe.shutdown();
		for (Future<Integer> future : list) {
			if (future.isDone())
				System.out.println(future.get());
		}
	}
}
