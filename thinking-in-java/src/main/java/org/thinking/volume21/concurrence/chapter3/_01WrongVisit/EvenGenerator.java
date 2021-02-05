package org.thinking.volume21.concurrence.chapter3._01WrongVisit;

public class EvenGenerator extends IntGenerator {
	/**
	 * ��ȫ�ֱ�������Ϊprivate��ʮ�ֱ�Ҫ�ģ�
	 * synchronized���ܷ�ֹ��������ֱ�ӷ���ȫ�ֱ���
	 * ����ж�������ڴ����ٽ����ݣ�����Щ��������Ҫͬ��
	 */
	private int currentEvenValue=0;
	
	@Override
 	public  int next() {
		/**
		 * ����������A�����п�����B������ִ�д˴��ĵ���������
		 * û�н�����һ������������������next()����,�Ӷ����������
		 * synchronized��֤һֱ��ż��
		 * ��������synchronized��������ͬһ����
		 * mutex
		 */
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		
		return currentEvenValue;
	}
}
