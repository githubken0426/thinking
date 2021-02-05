package  org.thinking.volume21.concurrence.chapter3._02Synchronized._06CriticalOther;

public class DualSych {
	
	private Object syncObject = new Object();
	/**
	 * f()����synchronized(this) �˴���this��DualSych
	 */
	public synchronized void f() {
		System.out.println("f():"+Thread.currentThread());
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	/**
	 * g()��������syncObject��ͬ����
	 */
	public void g() {
		synchronized (syncObject) {
			System.out.println("g():"+Thread.currentThread());
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}

	/**
	 * f()��g()����ͬ���ǻ�������ģ��������ڲ�ͬ�Ķ�����ͬ��
	 * ���Ƕ����Խ���ͬһ������main�߳�
	 * ������Կ�����������ͬ�����У�û�в�������
	 * ��ͬһ��������ͬ����ͬ������ֻ����һ���������������������ֱ����ǰ�������
	 * @param args
	 */
	public static void main(String[] args) {
		final DualSych ds = new DualSych();
		new Thread("F") {
			public void run() {
				ds.f();
			}
		}.start();
		System.out.println("____________________________");
		ds.g();
	}
}
