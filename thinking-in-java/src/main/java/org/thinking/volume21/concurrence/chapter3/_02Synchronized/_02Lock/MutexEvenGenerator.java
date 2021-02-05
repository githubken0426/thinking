package org.thinking.volume21.concurrence.chapter3._02Synchronized._02Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.thinking.volume21.concurrence.chapter3._01WrongVisit.EvenChecker;
import org.thinking.volume21.concurrence.chapter3._01WrongVisit.IntGenerator;

/**
 * ������ʽ������� Lock
 * 
 * @author Administrator
 *
 */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	// ��ƽ��
	private Lock lock = new ReentrantLock(true);

	/**
	 * return������try������ɣ�����unlock()�󣬾ͼӲ����� lock��synchronized�ŵ㣺
	 * synchronizedʧ�ܻ��׳��쳣��lock�׳������ܴ����쳣
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
