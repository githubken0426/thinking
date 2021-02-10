package org.thinking.volume21.concurrence.chapter3._01WrongVisit;

public abstract class IntGenerator {
	private volatile boolean cancel = false;

	public void cacel() {
		cancel = true;
	}

	public boolean isCancel() {
		return cancel;
	}

	public abstract int next();
}
