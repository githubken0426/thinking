package org.thinking.volume21.concurrence.chapter2._12Form;

import java.io.IOException;

public class ResponseUI extends Thread {
	private static volatile double d = 1;

	public ResponseUI() {
		setDaemon(true);
		start();
	}

	public void run() {
		System.out.println("�ػ��߳�");
		while (true)
			d += (Math.PI + Math.E) / d;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(Math.E);
		new ResponseUI();
		System.in.read();
		System.out.println(d);
	}
}
