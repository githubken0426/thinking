package cn.thinking.concurrent.exectors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * newSingleThreadExecutor()类似newFixedThreadPool(1)
 * 如果向newSingleThreadExecutor()提交多个任务，多个任务将会排队，
 * 在所有线程池中，现有线程有可能的情况下，都会被复用
 * @author Administrator
 * 2016-1-18 下午04:17:42
 *
 */
public class SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService exe=Executors.newSingleThreadExecutor();
		for(int i =0;i<2;i++){
			exe.execute(new Thread());
		}
		exe.shutdown();
	}
}
