package org.thinking.volume21.concurrence.chapter2._02Thread;

public class ThreadState {
	public static void main(String[] args) {
		System.out.println("*********************************************");
		Object object = new Object();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (object) {
					// 0.线程1在这里sleep是为了：
					// 1.让主线程输出线程1或者线程2的TIMED_WAITING状态
					// 2.让线程2保持BLOCKED状态，让主线程输出
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						// 0.执行wait方法后，线程1进入WAITING状态，并且释放锁
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 0.线程2在这里sleep就是为了保证线程1先拿到锁
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				synchronized (object) {
					// 0.线程2在这里sleep就是为了线程1保持WAITING的状态，让Main线程输出
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 0.线程2唤醒线程1，两个线程都执行完
					object.notifyAll();
				}
			}
		});
		
		System.out.println("main 0: t1 = " + t1.getState());// NEW
		t1.start();
		t2.start();
		System.out.println("main 0: t1 = " + t1.getState());// RUNNABLE
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 500： t2 = " + t2.getState());// TIMED_WAITING
		
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 1250： t2 = " + t2.getState());// BLOCKED
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 1750： t1 = " + t1.getState() );// WAITING
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 2250： t1 = " + t1.getState());//TERMINATED
	}

	/**
	 * 与wating状态相关联的是等待队列，与blocked状态相关的是同步队列;
	 * 一个线程由等待队列迁移到同步队列时，线程状态将会由wating转化为blocked;
	 * 可以这样说，blocked状态是处于wating状态的线程重新焕发生命力的必由之路。
	 */
	static {
		/**
		 * Thread state for a thread which has not yet started.
		 */
		System.out.println(Thread.State.NEW);
		/**
		 * Thread state for a runnable thread. A thread in the runnable state is
		 * executing in the Java virtual machine but it may be waiting for other
		 * resources from the operating system such as processor.
		 */
		System.out.println(Thread.State.RUNNABLE);
		/**
		 * Thread state for a thread blocked waiting for a monitor lock.
		 * A thread in the blocked state is waiting for a monitor lock to enter a synchronized block/method 
		 * or reenter a synchronized block/method after calling
		 * {@link Object#wait() Object.wait}.
		 */
		System.out.println(Thread.State.BLOCKED);
		/**
		 * Thread state for a waiting thread. A thread is in the waiting state due to
		 * calling one of the following methods:
		 * <ul>
		 * <li>{@link Object#wait() Object.wait} with no timeout</li>
		 * <li>{@link #join() Thread.join} with no timeout</li>
		 * <li>{@link LockSupport#park() LockSupport.park}</li>
		 * </ul>
		 *
		 * <p>
		 * A thread in the waiting state is waiting for another thread to perform a particular action.
		 *
		 * For example, a thread that has called <tt>Object.wait()</tt> on an object is
		 * waiting for another thread to call <tt>Object.notify()</tt> or
		 * <tt>Object.notifyAll()</tt> on that object. A thread that has called
		 * <tt>Thread.join()</tt> is waiting for a specified thread to terminate.
		 */
		System.out.println(Thread.State.WAITING);
		/**
		 * Thread state for a waiting thread with a specified waiting time. A thread is
		 * in the timed waiting state due to calling one of the following methods with a
		 * specified positive waiting time:
		 * <ul>
		 * <li>{@link #sleep Thread.sleep}</li>
		 * <li>{@link Object#wait(long) Object.wait} with timeout</li>
		 * <li>{@link #join(long) Thread.join} with timeout</li>
		 * <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
		 * <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
		 * </ul>
		 */
		System.out.println(Thread.State.TIMED_WAITING);
		/**
		 * Thread state for a terminated thread. The thread has completed execution.
		 */
		System.out.println(Thread.State.TERMINATED);
	}
}
