package org.thinking.volume21.concurrence.exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise_10 {
	private static ExecutorService exe;
	
	private static int fib(int n){
		if(n<2)return 1;
		int result=fib(n-2)+fib(n-1);
		return result;
	}
	
	public static synchronized Future<Integer> runTask(final int n){
		/**
		 * assert:���Ա�ʾΪһЩ�������ʽ������Ա�����ڳ����е�ĳ���ض���ñ��ʽֵΪ��
		 * ��Ҫ��ʽ����
		 */
		assert exe!=null;
		return exe.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				int sum=0;
				for(int i =0;i<n;i++)
					sum+=fib(i);
				return sum;
			}
		});
	}
	
	//��ʼ��
	public static void init(){
		if(exe==null)
			exe=Executors.newCachedThreadPool();
	}
	//�ر�
	public static void shutdown(){
		if(exe!=null)
			exe.shutdown();
		exe=null;
	}
	
	public static void main(String[] args) {
		List<Future<Integer>> list=new ArrayList<Future<Integer>>();
		Exercise_10.init();
		for(int i=1;i<=5;i++)
			list.add(Exercise_10.runTask(i));
		Thread.yield();
		Exercise_10.shutdown();
		for (Future<Integer> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException  e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
