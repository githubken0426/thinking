package org.thinking.volume21.concurrence.chapter4._03Interrupted._method1;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.thinking.volume21.concurrence.chapter4._03Interrupted.IOBlocked;

/**
 * �԰�����I/O�������̣߳������жϲ�����
 * ����web����غ�����
 * ����ͨ���ر��������Ϸ��������ĵײ���Դ
 * @author Administrator
 *
 */
public class CloseResource {
	public static void main(String[] args) throws Exception {
		ExecutorService exe=Executors.newCachedThreadPool();
		ServerSocket server=new ServerSocket(8080);
		@SuppressWarnings("resource")
		InputStream input=new Socket("localhost",8080).getInputStream();
		
		exe.execute(new IOBlocked(input));
//		exe.execute(new IOBlocked(System.in));
		
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads!");
		//shutdownNow�ᷢ��һ���жϲ���
		exe.shutdownNow();
		
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Closing "+input.getClass().getName());
		input.close();
		
		TimeUnit.SECONDS.sleep(2);
//		System.out.println("Closing "+System.in.getClass().getName());
//		System.in.close();
		
		server.close();
		
	}
}
