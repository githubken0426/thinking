package org.thinking.volume21.concurrence.exercise;

/**
 * ��ϵ1
 * @author Administrator
 * 2016-1-18 ����11:39:35
 *
 */
public class Exercise_01 implements Runnable{
	private static int taskCount;
	private final int id=taskCount++;
	public Exercise_01(){
		System.out.println(Thread.currentThread().getName()+"��Exercise_01:"+id);
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+":"+id);
		Thread.yield();
		System.out.println(Thread.currentThread().getName()+":"+id);
		Thread.yield();
		System.out.println(Thread.currentThread().getName()+":"+id);
		Thread.yield();
	}
	
	/**
	 *  1�� start����start�����������̣߳�����ʵ���˶��߳����У�
	 *  ��ʱ����ȴ�run������,����ִ����϶�ֱ�Ӽ���ִ������Ĵ��롣
	 *  ͨ������Thread���start()����������һ���̣߳�
	 *  ��ʱ���̴߳��ھ����������У�״̬����û�����У�һ���õ�cpuʱ��Ƭ��
	 *  �Ϳ�ʼִ��run()���������﷽�� run()��Ϊ�߳��壬��������Ҫִ�е�����̵߳����ݣ�
	 *  Run�������н��������߳��漴��ֹ��
	 *  
	 *  2�� run��run()����ֻ�����һ����ͨ�������ѣ����ֱ�ӵ���Run������
	 *  ��������Ȼֻ�����߳���һ���̣߳������ִ��·������ֻ��һ��������Ҫ˳��ִ�У�
	 *  ����Ҫ�ȴ�run������ִ����Ϻ�ſɼ���ִ������Ĵ��룬������û�дﵽд�̵߳�Ŀ�ġ�
	 *  
	 *  �ܽ᣺����start�������������̣߳���run����ֻ��thread��һ����ͨ�������ã����������߳���ִ�С�
	 *  start()���������߳̽��Զ����� run()������������jvm���ڴ���ƹ涨�ġ�
	 *  ����run()����������public����Ȩ�ޣ�����ֵ����Ϊvoid.
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=1;i<=3;i++){
			new Thread(new Exercise_01()).start();
//			new Exercise_01().run();
		}
		
	}

}
