package org.thinking.volume21.concurrence.chapter3._01WrongVisit;

public class EvenGenerator extends IntGenerator {
	/**
	 * 将全局变量设置为private是十分必要的， synchronized不能防止其他对象直接访问全局变量 如果有多个方法在处理临界数据，则这些方法都需要同步
	 */
	private int currentEvenValue = 0;

	@Override
	public int next() {
		/**
		 * 递增操作后，A任务有可能在B任务在执行此处的递增操作， 没有进行下一个递增操作，而调用next()方法,从而会产生奇数
		 * synchronized后保证一直是偶数 类中所有synchronized方法共享同一个锁 mutex
		 */
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;

		return currentEvenValue;
	}
}
