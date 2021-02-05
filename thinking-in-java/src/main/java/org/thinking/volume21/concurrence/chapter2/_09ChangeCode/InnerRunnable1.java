package org.thinking.volume21.concurrence.chapter2._09ChangeCode;

import java.util.concurrent.TimeUnit;

public class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;

	class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		@Override
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
			return t.getName() + ":" + countDown;
		}
	}

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}
