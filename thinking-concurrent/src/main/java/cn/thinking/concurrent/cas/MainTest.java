package cn.thinking.concurrent.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		AtomicInteger in = new AtomicInteger();
		in.addAndGet(1);

		ExecutorService es = Executors.newFixedThreadPool(12);
		ExecutorService sc = Executors.newSingleThreadExecutor();
		ScheduledExecutorService sc2 = Executors.newSingleThreadScheduledExecutor();
	}
}
