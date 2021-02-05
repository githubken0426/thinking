package org.thinking.volume21.concurrence.chapter3._02Synchronized._04AtomicClass;

import java.util.concurrent.atomic.AtomicInteger;

import org.thinking.volume21.concurrence.chapter3._01WrongVisit.EvenChecker;
import org.thinking.volume21.concurrence.chapter3._01WrongVisit.IntGenerator;

/**
 * ͨ�������������ȫһЩ
 * 
 * @author Administrator
 *
 */
public class AtomicEvenGenerator extends IntGenerator {
	private AtomicInteger currentValue = new AtomicInteger(0);

	@Override
	public int next() {
		return currentValue.addAndGet(2);
	}

	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
