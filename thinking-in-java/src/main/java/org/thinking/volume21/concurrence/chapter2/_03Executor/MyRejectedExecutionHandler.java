package org.thinking.volume21.concurrence.chapter2._03Executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		 if (!executor.isShutdown()) {
			 System.out.println("rejectedExecution:" + executor.getQueue().peek());
			 executor.getQueue().poll();
			 executor.execute(r);
         }
		
	}

}
