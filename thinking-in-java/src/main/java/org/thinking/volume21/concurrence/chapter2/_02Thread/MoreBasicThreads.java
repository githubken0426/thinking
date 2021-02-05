package org.thinking.volume21.concurrence.chapter2._02Thread;

import org.thinking.volume21.concurrence.chapter2._01Runnable.LiftOff;

/**
 * 无规律输出，有线程调度器自动控制
 * 不同版本的jdk输出有很大的差异
 * @author Administrator
 * 2016-1-18 上午10:51:00
 *
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for(int i=0;i<5;i++)
			new Thread(new LiftOff()).start();
		System.out.println(Thread.currentThread().getName()+":Wait for LiftOff");
	}
}
