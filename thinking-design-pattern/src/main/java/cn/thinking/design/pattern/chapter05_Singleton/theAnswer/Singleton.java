package cn.thinking.design.pattern.chapter05_Singleton.theAnswer;

public class Singleton {
	private static Singleton instance;
	private Singleton() {
	}

	/**
	 * 1.非线程安全
	 */
	public static Singleton singleInstance1() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	/**
	 * 2.效率较低 大多情况下不需要同步
	 * 
	 * @return 2017-5-12 上午11:27:57
	 */
	public static synchronized Singleton singleInstance2() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	/**
	 * 3.基于classloder机制避免了多线程的同步问题 不过，instance在类装载时就实例化,
	 * 这时候初始化instance显然没有达到lazy loading的效果
	 * 
	 * @return 2017-5-12 上午11:27:57
	 */
	private static Singleton singel = new Singleton();

	public static Singleton singleInstance3() {
		return singel;
	}

	/**
	 * 4.内部类实现 即使Singleton类被装载了，instance不一定被初始化
	 * 
	 * @author ken 2017-5-12 上午11:48:14
	 */
	private static class HolderSingle {
		private static Singleton SINGEL = new Singleton();
	}

	public static Singleton singleInstance4() {
		return HolderSingle.SINGEL;
	}

	/**
	 * 5.枚举实现
	 * 
	 * @author ken 2017-5-12 上午11:54:21
	 */
	public static enum EnumSingle {
		SINGEL {
			@Override
			public Singleton getInstance() {
				System.out.println("枚举调用");
				return new Singleton();
			}
		};
		public abstract Singleton getInstance();
	}

	/**
	 * 6.双重校验锁
	 * 1.4之前的版本会导致voldatile失效
	 * @return 2017-5-12 下午12:00:23
	 */
	private static volatile Singleton instance2;
	public static Singleton singleInstance5() {
		//只有第一次创建时才会进入此代码块
		if (instance2 == null) {
			synchronized (Singleton.class) {
				if (instance2 == null)
					instance2 = new Singleton();
			}
		}
		return instance2;
	}

	public static void main(String[] args) {
		Singleton singel = EnumSingle.SINGEL.getInstance();
	}
}
