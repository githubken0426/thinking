package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.thinking.volume21.concurrence.chapter3._01WrongVisit.EvenChecker;
import org.thinking.volume21.concurrence.chapter3._01WrongVisit.IntGenerator;

/**
 * 运用显式互斥机制 Lock
 * 
 * @author Administrator
 *
 */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	/**
	 * 默认非公平锁。
	 * NonfairSync导致某个线程因为所需要的资源被其他线程占用而得不到CPU调度，那这种情况就被称之为“饥饿”。
	 * 如果其他线程一直占用资源，那饥饿的线程就会被“饿死”（类似死锁的情况，一直在阻塞等待某个资源，或者运气比较差，每次都没有抢到）。
	 * 解决饥饿问题，通常使用"FairSync"策略，来保障所有的线程都有机会被CPU调度。
	 */
	private Lock lock = new ReentrantLock(true);

	/**
	 * return必须在try里面完成，否则unlock()后，就加不上锁 。
	 * 
	 * 自旋锁（spinlock）：是指当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。
	 * 
	 * 1、两者所处层面不同
	 * synchronized是Java中的一个关键字，当我们调用它时会从在虚拟机指令层面加锁，关键字为monitorenter和monitorexit。
	 * Lock是Java中的一个接口，它有许多的实现类来为它提供各种功能，加锁的关键代码为大体为Lock和unLock。
	 * 
	 * 2、获锁方式
	 * synchronized可对实例方法、静态方法和代码块加锁，相对应的，加锁前需要获得实例对象的锁或类对象的锁或指定对象的锁。说到底就是要先获得对象的监视器（即对象的锁）然后才能够进行相关操作。
	 * Lock的使用离不开它的实现类AQS，而它的加锁并不是针对对象的，而是针对当前线程的，并且AQS中有一个原子类state来进行加锁次数的计数 。
	 * 
	 * 3、获锁失败
	 * 使用关键字synchronized加锁的程序中，获锁失败的对象会被加入到一个虚拟的等待队列中被阻塞，直到锁被释放；1.6以后加入了自旋操作;
	 * 使用Lock加锁的程序中，获锁失败的线程会被自动加入到AQS的等待队列中进行自旋，自旋的同时再尝试去获取锁，等到自旋到一定次数并且获锁操作未成功，线程就会被阻塞。
	 * 
	 * 4、偏向或重入 
	 * synchronized中叫做偏向锁 当线程访问同步块时，会使用 CAS 将线程 ID 更新到锁对象的 Mark Word中，
	 * 如果更新成功则获得偏向锁，并且之后每次进入这个对象锁相关的同步块时都不需要再次获取锁了。 
	 * Lock中叫做重入锁,AQS的实现类ReentrantLock实现了重入的机制，即若线程a已经获得了锁，a再次请求锁时则会判断a是否持正有锁，然后会将原子值state+1来实现重入的计数操作。
	 * 
	 * 5、Lock独有的队列
	 * condition队列是AQS中的一个Lock的子接口的内部现类，它一般会和ReentrantLock一起使用来满足除了加锁和解锁以外的一些附加条件，比如对线程的分组和临界数量的判断（阻塞队列）。
	 * 
	 * 6、解锁操作 
	 * synchronized：不能指定解锁操作，执行完代码块的对象会自动释放锁。
	 * Lock：可调用ulock方法去释放锁比synchronized更灵活。
	 * 
	 */
	@Override
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			return currentEvenValue;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}
}
