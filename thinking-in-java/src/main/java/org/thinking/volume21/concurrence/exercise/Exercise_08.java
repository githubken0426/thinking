package org.thinking.volume21.concurrence.exercise;
import java.util.concurrent.TimeUnit;

import org.thinking.volume21.concurrence.chapter2._01Runnable.LiftOff;

/**
 * ����LiftOffΪ��̨�̺߳󣬻����cpuʱ��Ƭ�����������(����toString()����)
 * debug��ȫ�����5���߳���
 * @author Administrator
 *
 */
public class Exercise_08 {
	public static void main(String[] args) {
		
		for(int i=0;i<5;i++){
			Thread t=new Thread(new LiftOff());
			t.setDaemon(true);
			t.start();
		}
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting For LlftOff!");
	}
}
