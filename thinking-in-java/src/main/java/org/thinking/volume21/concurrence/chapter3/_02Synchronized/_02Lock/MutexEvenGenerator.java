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
	// 公平锁
	private Lock lock = new ReentrantLock(true);

	/**
	 * return必须在try里面完成，否则unlock()后，就加不上锁 lock比synchronized优点：
	 * synchronized失败会抛出异常，lock抛出后则能处理异常
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
