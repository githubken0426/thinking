package cn.thinking.design.pattern.chapter08_Template.theAnswer;

public abstract class CaffeineBeverageWithHook {
	final void prepare() {
		boilWater();
		brew();
		if (hook())
			addCondiments();
		pourInCup();
	}

	/**
	 * 两个原语操作，有子类实现 由若干个指令构成的完成某种特定功能的一段程序，具有不可分割性· 
	 * 即原语的执行必须是连续的，在执行过程中不允许被中断
	 * 
	 * @throws @date
	 *   2017年8月2日 下午3:17:38
	 */
	abstract void brew();

	abstract void addCondiments();

	final void boilWater() {
		System.out.println("boiling water!");
	}

	final void pourInCup() {
		System.out.println("Pouring into cup!");
	}

	/**
	 * 钩子 让子类决定是不是挂钩
	 * 
	 * @return
	 * @throws @date
	 *             2017年8月2日 下午3:19:59
	 */
	boolean hook() {
		return true;
	}
}
