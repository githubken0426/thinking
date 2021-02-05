package org.thinking.volume21.concurrence.chapter3._02Synchronized._06CriticalOther;

import org.thinking.volume21.concurrence.chapter3._01WrongVisit.EvenChecker;
import org.thinking.volume21.concurrence.chapter3._01WrongVisit.IntGenerator;

/**
 * synchronized java��װ��
 * 
 * @author Administrator 2016-5-3 ����04:18:40
 *
 */
public class SynchronizedEncapsulation {

	/**
	 * ���Կ����Է�װ���ͬ��������Ч ���Ӧ����װ�䡢�����ԭ�� ���ǽ���AtomicInteger����ԭ����
	 * 
	 * @author Administrator 2016-5-3 ����04:32:57
	 *
	 */
	static class IntegerSynchronized extends IntGenerator {
		private Double i = new Double(0);

		@Override
		public int next() {
			synchronized (i) {
				i++;
				i++;
			}
			return i.intValue();
		}
	}

	public static void main(String[] args) {

		EvenChecker.test(new IntegerSynchronized(), 10);

	}
}
