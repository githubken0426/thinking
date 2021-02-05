package org.thinking.volume12.exception.chapter4;

public class ParcelLogger {
	public static void main(String[] args) {
		try {
			throw new LoggerException();
		} catch (LoggerException e) {
			System.err.println("Caught:" + e);
		}
		try {
			throw new LoggerException();
		} catch (LoggerException e) {
			System.err.println("Caught2:" + e);
		}

	}
}
