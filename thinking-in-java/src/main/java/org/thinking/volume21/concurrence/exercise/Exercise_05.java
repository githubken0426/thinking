package org.thinking.volume21.concurrence.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import  org.thinking.TIJ4_code.net.mindview.util.Generator;

/**
 * ��ϰ5
 * @author Administrator
 * 2016-1-18 ����04:42:42
 *
 */
public class Exercise_05 implements Generator<Integer>,Callable<Integer> {
	private int count;
	private final int n;
	
	public Exercise_05(int n){
		this.n =n;
	}
	
	private static int fib(int n){
		if(n<2)return 1;
		int result=fib(n-2)+fib(n-1);
		return result;
	}
	
	@Override
	public Integer next() {
		return fib(count++);
	}

	@Override
	public Integer call() throws Exception {
		int sum=0;
		for(int i=0;i<n;i++)
			sum+=next();
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exe=Executors.newFixedThreadPool(4);
		List<Future<Integer>> list=new ArrayList<Future<Integer>>();
		for(int i =1;i<=5;i++)
			list.add(exe.submit(new Exercise_05(i)));
		Thread.yield();
		exe.shutdown();
		/**
		 * Future���Ƕ��ھ����Runnable����Callable�����ִ�н������ȡ������ѯ�Ƿ���ɡ���ȡ�����
		 * ��Ҫʱ����ͨ��get������ȡִ�н�����÷���������ֱ�����񷵻ؽ��
		 */
		for (Future<Integer> future : list) {
			System.out.println(future.get());
		}
	}
}
