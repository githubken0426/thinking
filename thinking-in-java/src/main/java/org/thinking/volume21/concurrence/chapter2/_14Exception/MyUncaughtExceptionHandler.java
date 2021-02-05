package org.thinking.volume21.concurrence.chapter2._14Exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * UncaughtExceptionHandler
 * @author Administrator
 *
 */
public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("MyUncaughtExceptionHandler:["+e+"]t.getName():"+t.getName());
	}

}
