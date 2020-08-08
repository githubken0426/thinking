package cn.thinking.design.pattern.chapter05_Singleton.anExample;

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
	
	public static Singleton getInstance(){
		if(uniqueInstance==null)
			return new Singleton();
		return uniqueInstance;
	}
}
