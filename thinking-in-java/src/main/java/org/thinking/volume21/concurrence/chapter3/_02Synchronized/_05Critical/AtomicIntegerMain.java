package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerMain {
	
/**
 * ��ȡ��ǰ��ֵ public final int get()
 * ȡ��ǰ��ֵ���������µ�ֵ public final int getAndSet(int newValue)
 * ��ȡ��ǰ��ֵ�������� public final int getAndIncrement() 
 * ��ȡ��ǰ��ֵ�����Լ� public final int getAndDecrement()
 * ��ȡ��ǰ��ֵ��������Ԥ�ڵ�ֵpublic final int getAndAdd(int delta)
 * 
 * @param args
 */
	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(0);
		int i1 = ai.get();
		System.out.println("i1="+i1);
		int i2 = ai.getAndSet(5);
		System.out.println("i2="+i2);
		int i3 = ai.get();
		System.out.println("i3="+i3);
		int i4 = ai.getAndIncrement();
		System.out.println("i4="+i4);
		System.out.println("ai.get()="+ai.get());
	}
}
