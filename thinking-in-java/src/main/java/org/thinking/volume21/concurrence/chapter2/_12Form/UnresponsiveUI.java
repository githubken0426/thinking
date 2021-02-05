package org.thinking.volume21.concurrence.chapter2._12Form;

public class UnresponsiveUI {
	private volatile double d = 1;

	public UnresponsiveUI() throws Exception {
		while (true)
			d += (Math.PI + Math.E) / d;
//		System.in.read();
	}
}
