package  org.thinking.volume21.concurrence.chapter3._02Synchronized._04AtomicClass;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ͨ��ԭ����AtomicInteger������synchronized,���򲻻�ʧ��
 * @author Administrator
 *
 */
public class AtomicIntegerTest implements Runnable {
	
	private AtomicInteger atomic=new AtomicInteger(0);
	
	public int getValue(){
		return atomic.get();
	}
	
	public void evenIncrement(){
		atomic.addAndGet(2);
	}
	
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("����ʱ�䵽��");
				System.exit(0);
			}
		}, 5000);
		
		ExecutorService exe=Executors.newCachedThreadPool();
		AtomicIntegerTest test=new AtomicIntegerTest();
		exe.execute(test);
		
		while(true){
			int val=test.getValue();
			System.out.println(val);
			if(val %2!=0){
				System.out.println(val+"����ż����");
				System.exit(0);
			}
		}
	}
}
