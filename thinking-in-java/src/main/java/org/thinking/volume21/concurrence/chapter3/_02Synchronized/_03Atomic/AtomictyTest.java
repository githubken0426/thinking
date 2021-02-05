package  org.thinking.volume21.concurrence.chapter3._02Synchronized._03Atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * һ��ԭ����
 * ԭ���������ϵ���С��λ�����в��ɷָ��ԡ����� a=0����a��long��double���ͣ� ��������ǲ��ɷָ�ģ�
 * ��ô����˵�������ʱԭ�Ӳ������ٱ��磺a++�� �������ʵ����a = a + 1���ǿɷָ�ģ�����������һ��ԭ�Ӳ�����
 * ��ԭ�Ӳ�����������̰߳�ȫ���⣬��Ҫ����ʹ��ͬ��������synchronized�����������һ��ԭ�Ӳ�����
 * һ��������ԭ�Ӳ�������ô���ǳ�������ԭ���ԡ�java��concurrent�����ṩ��һЩԭ���࣬
 * ���ǿ���ͨ���Ķ�API���˽���Щԭ������÷������磺AtomicInteger��AtomicLong��AtomicReference�ȡ�
 * 
 * �����ɼ���
 * ��ָ�߳�֮��Ŀɼ��ԣ�һ���߳��޸ĵ�״̬����һ���߳��ǿɼ��ġ�
 * Ҳ����һ���߳��޸ĵĽ������һ���߳����Ͼ��ܿ�����
 * ���磺��volatile���εı������ͻ���пɼ��ԡ�
 * volatile���εı����������߳��ڲ�����������򣬼�ֱ���޸��ڴ档
 * ���Զ������߳��ǿɼ��ġ�����������Ҫע��һ�����⣬volatileֻ���ñ����������ݾ��пɼ��ԣ�
 * �����ܱ�֤������ԭ���ԡ����� volatile int a = 0��֮����һ������ a++���������a���пɼ��ԣ�
 * ����a++ ��Ȼ��һ����ԭ�Ӳ�����Ҳ�����������ͬ�������̰߳�ȫ���⡣
 * 
 * JVM�Ὣ64λ��long��double�Ķ�ȡ��д��ָ������32λ������ִ��
 * ��Ͳ����˶�ȡ��д����������л����Ӷ����²�ͬ�����������ͬ�Ľ��
 * volatile��java se5������ԣ�֮ǰ����֧�֣�������ò�Ҫ����ƽ̨����
 * @author Administrator
 *
 */

public class AtomictyTest implements Runnable {
	private volatile int i=0;//��������volatile����,���ڿ���������
	
	/**
	 * ��ȻevenCrement()������ͬ���ģ�getValue()������return iҲ��ԭ���Բ�����
	 * ����ȱ��ͬ��ʹ��i���м�״̬ʱ(JVM get��putʱ)�򱻶�ȡ
	 * @return
	 */
	public  int getValue(){//synchronized
		return i;
	}
	
	private synchronized void evenCrement(){
		++i;
		++i;
	}
	
	@Override
	public void run() {
		while(true)
			evenCrement();
	}

	public static void main(String[] args) {
		ExecutorService exe=Executors.newCachedThreadPool();
		AtomictyTest atom = new AtomictyTest();
		exe.execute(atom);
		while(true){
			int val=atom.getValue();
			if(val %2!=0){
				System.out.println("������"+val);
				System.exit(0);
			}
		}
	}
}
