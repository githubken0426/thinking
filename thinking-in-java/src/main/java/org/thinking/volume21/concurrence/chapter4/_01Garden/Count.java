package org.thinking.volume21.concurrence.chapter4._01Garden;

import java.util.Random;

public class Count {
	private int count=0;
	private Random random=new Random(47);
	
	public synchronized int incrment(){
		int temp=count;//����������ֵ������Ϊ�����Ӽ�����̼��Ӵ��˳���ʧ�ܵĿ�����
		if(random.nextBoolean())//Ϊ�����̸߳���Ĳ����ò�
			Thread.yield();
		return (count=++temp);
	}
	
	public synchronized int value(){
		return count;
	}
	
}
