package cn.thinking.design.pattern.chapter05_Singleton.sychronized;

/**
 * 单例模式
 * lazy Initialize
 * @author ken
 * 
 * @date 2017年7月10日 下午5:11:42
 */
public class Singleton {
	public static Singleton uniqueInstance;
	private Singleton(){}
	
	/**
	 * 并发时破坏单例
	 * 使用synchronized会大大降低程序的效率
	 * @return
	 * @date 2017年7月10日 下午5:17:28
	 */
	public static synchronized Singleton getInstance(){
		if(uniqueInstance==null)
			return new Singleton();
		return uniqueInstance;
	}
}
