package cn.thinking.concurrent.exectors;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService,是基于线程池设计的定时任务类,
 * 每个调度任务都会分配到线程池中的一个线程去执行,
 * 需要注意,只有当调度任务来的时候,ScheduledExecutorService才会真正启动一个线程,
 * 其余时间ScheduledExecutorService都是出于轮询任务的状态
 * 
 * @return 2017-3-21 下午05:05:47
 */
public class ThreadScheduledExecutor {

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(5);
//		 testAtFixedRate(scheduled);
//		 testAtFixedRateWithException(scheduled);

		 testFixedDelay(scheduled);
		// testSchedule(scheduled);
		System.out.println("当前时间" + format.format(LocalDateTime.now()));
//		testSchedule(scheduled, 3);
	}

	/**
	 * 将在initialDelay(2秒)+period(3秒)后执行， 此后将以period(3秒)来执行，依此类推
	 * 如果sleep时间大于period，将在sleep时间后执行
	 */
	public static void testAtFixedRate(ScheduledExecutorService scheduled) {
		scheduled.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(format.format(new Date()));
			}
		}, 2, 3, TimeUnit.SECONDS);
	}

	public static void testAtFixedRateWithException(
			ScheduledExecutorService scheduled) {
		// ��ʼִ�к�ʹ����쳣,���ڽ���������
		scheduled.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("RuntimeException no catch,next time can't run");
				throw new RuntimeException();
			}
		}, 2, 3, TimeUnit.SECONDS);

		// ��Ȼ�׳��������쳣,����������,���ڼ�������
		scheduled.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println(format.format(new Date()));
				try {
					throw new RuntimeException();
				} catch (Exception e) {
					System.out.println("RuntimeException catched,can run next");
				}
			}
		}, 2, 3, TimeUnit.SECONDS);
	}

	/**
	 * ����initialDelay(2��)��ִ�У� �˺���period(3��)+sleep(1��)��ִ�У���������
	 * @param scheduled
	 * 2017-3-22 ����11:55:42
	 */
	public static void testFixedDelay(ScheduledExecutorService scheduled) {
		scheduled.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				System.out.println(format.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2, 3, TimeUnit.SECONDS);

	}

	/**
	 * ������ִ���ڸ����ӳٺ����õ�һ��������
	 * @param scheduled
	 * 			ScheduledExecutorServiced����
	 * 2017-3-22 ����11:55:13
	 */
	public static void testSchedule(ScheduledExecutorService scheduled) {
		scheduled.schedule(new Runnable() {
			public void run() {
				System.out.println("The thread can only run once!");
				System.out.println(format.format(new Date()));
			}
		}, 3, TimeUnit.SECONDS);
	}
	/**
	 * ����ScheduleFuture�ӿڣ�ScheduleFuture�̳���Future�ӿ�
	 * @param <T>
	 * @param scheduled
	 * 			ScheduledExecutorServiced����
	 * @param min
	 * 2017-3-22 ����11:52:58
	 */
	public static <T> void testSchedule(ScheduledExecutorService scheduled,
			long min) {
		try {
			ScheduledFuture<T> futrue = scheduled.schedule(new Callable<T>() {
				@SuppressWarnings("unchecked")
				@Override
				public T call() throws Exception {
					return (T) format.format(new Date());
				}

			}, min, TimeUnit.SECONDS);

			System.out.println("�߳�ʱ�䣺" + futrue.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
