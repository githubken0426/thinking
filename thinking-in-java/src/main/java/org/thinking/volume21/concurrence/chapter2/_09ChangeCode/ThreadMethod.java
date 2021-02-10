package org.thinking.volume21.concurrence.chapter2._09ChangeCode;

import java.util.concurrent.TimeUnit;

public class ThreadMethod {
	private int countDown = 5;
	private String name;
	private Thread t;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		t = new Thread(name) {
			public void run() {
				try {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						TimeUnit.SECONDS.sleep(2);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Interrupter");
				}
			}

			public String toString() {
				return getName() + ":" + countDown;
			}
		};
		t.start();
	}
}
