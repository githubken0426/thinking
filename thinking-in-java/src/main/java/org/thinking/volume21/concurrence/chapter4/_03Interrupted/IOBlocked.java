package org.thinking.volume21.concurrence.chapter4._03Interrupted;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
	private InputStream in;
	
	public IOBlocked(InputStream in){
		this.in=in;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("wait for read");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted()){
				System.out.println("InterruptedException IOBlocked�ж�catch��!");
			}else{
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}

}
