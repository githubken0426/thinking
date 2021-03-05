package cn.thinking.concurrent.cas;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class CompareAndSwap {
	private final AtomicInteger aInt = new AtomicInteger();

	/**
	 * CAS（Compare-and-Swap），即比较并替换，是一种实现并发算法时常用到的技术，Java并发包中的很多类都使用了CAS技术。
	 * 
	 * 
	 * CAS虽然很高效的解决了原子操作问题，但是CAS仍然存在三大问题。
	 * 1,循环时间长开销很大:如果CAS失败，会一直进行尝试。如果CAS长时间一直不成功，可能会给CPU带来很大的开销。
	 * 2,只能保证一个共享变量的原子操作:当对一个共享变量执行操作时，我们可以使用循环CAS的方式来保证原子操作，
	 * 	但是对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁来保证原子性。
	 * 3,ABA问题:如果在这段期间它的值曾经被改成了B，后来又被改回为A，那CAS操作就会误认为它从来没有被改变过。这个漏洞称为CAS操作的“ABA”问题。
	 * Java并发包为了解决这个问题，提供了一个带有标记的原子引用类“AtomicStampedReference”，它可以通过控制变量值的版本来保证CAS的正确性。
	   *       因此，在使用CAS前要考虑清楚“ABA”问题是否会影响程序并发的正确性，如果需要解决ABA问题，改用传统的互斥同步可能会比原子类更高效。
	 * 	“ABA”从二进制上来看是依旧是 A， 但是其语义已经不是 A。
	 * @return
	 */
	public final int getAndIncrement() {
		for (;;) {
			int current = aInt.get();
			int next = current + 1;
			if (compareAndSet(current, next))
				return current;
		}
	}

	/**
	 * CAS需要有3个操作数：内存地址V，旧的预期值A，即将要更新的目标值B。
	 * CAS指令执行时，当且仅当内存地址V的值与预期值A相等时，将内存地址V的值修改为B，否则就什么都不做。整个比较并替换的操作是一个原子操作。
	 * 
	 * @param current
	 * @param next
	 * @return
	 */
	private boolean compareAndSet(int current, int next) {
		int v = aInt.get();// mock
		if (v == current) {
			v = next;
			return true;
		}
		return false;
	}

	
	/** 
	 * AbortPolicy 该策略会直接抛出异常，阻止系统正常 工作。线程池默认为此。
	 * CallerRunsPolicy只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。
	 * DiscardOledestPolicy该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试重新提交当前任务。
	 * DiscardPolicy该策略默默地丢弃无法处理的任务，不予任务处理。
	 */
	private volatile RejectedExecutionHandler handler;
	private volatile long keepAliveTime;
	private volatile int corePoolSize;
	private volatile int maximumPoolSize;
	/**
	 * SynchronousQueue 直接提交队列：没有容量，每一个插入操作都要等待一个相应的删除操作。通常使用需要将maximumPoolSize的值设置很大，否则很容易触发拒绝策略。
	 * ArrayBlockingQueue 有界的任务队列：任务大小通过入参 int capacity决定，当填满队列后才会创建大于corePoolSize的线程。
	 * LinkedBlockingQueue 无界的任务队列：线程个数最大为corePoolSize，如果任务过多，则不断扩充队列，知道内存资源耗尽。
	 * PriorityBlockingQueue 优先任务队列：是一个无界的特殊队列，可以控制任务执行的先后顺序，而上边几个都是先进先出的策略。
	 */
	private final BlockingQueue<Runnable> workQueue;
	/**
	 * DefaultThreadFactory,PrivilegedThreadFactory
	 */
	private volatile ThreadFactory threadFactory;

	public CompareAndSwap(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		if (corePoolSize < 0 || maximumPoolSize <= 0 || maximumPoolSize < corePoolSize || keepAliveTime < 0)
			throw new IllegalArgumentException();
		if (workQueue == null || threadFactory == null || handler == null)
			throw new NullPointerException();
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.workQueue = workQueue;
		this.keepAliveTime = unit.toNanos(keepAliveTime);
		this.threadFactory = threadFactory;
		this.handler = handler;
	}
}
