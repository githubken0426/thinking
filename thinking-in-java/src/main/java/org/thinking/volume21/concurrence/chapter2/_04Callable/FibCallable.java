package org.thinking.volume21.concurrence.chapter2._04Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.thinking.TIJ4_code.net.mindview.util.Generator;
/**
 * 斐波那契数列(Fibonacci sequence)，又称黄金分割数列、因数学家列昂纳多·斐波那契( LeonardodaFibonacci)以兔子繁殖为例子而引入，故又称为“兔子数列”，
 * 指的是这样一个数列:1、1、2、3、5、8、13、21、34、…
 * 在数学上，斐波纳契数列以如下被以递推的方法定义：F(1)=1，F(2)=1，F(3)=2，F(n)=F(n1)+F(n2)(n>=4，n∈N*)
 * 
 * @author kun.f.wang
 *
 * @param <V>
 */
public class FibCallable<V> implements Generator<Integer>, Callable<Integer> {
	private int count;
	private int index;

	public FibCallable(int index) {
		this.index = index;
	}

	public int fibonacci(int n) {
		if (n < 2)
			return 1;
		return fibonacci(n - 2) + fibonacci(n - 1);
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
		return fibonacci(count++);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		List<Future<Integer>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			//submit返回 FutureTask
			list.add(exe.submit(new FibCallable<Object>(i)));
		}
		exe.shutdown();
		for (Future<Integer> future : list) {
			if (future.isDone())
				System.out.println(future.get());
		}
	}
}
