package cn.thinking.concurrent.doublecheckedlocking;

import cn.thinking.singleton.Singleton;

public class DoubleCheckedLocking {
	/**
	   *  单例模式----双重校验锁
	   *  隐患
	   *  上述写法有个很大的隐患。实例化对象实际上可以分解成以下三个步骤：
	   *  a,分配内存空间
	   *  b,初始化对象
	   *  c,将对象指向刚分配的内存空间
	   *  但是有些编译器为了性能的原因，可能会将第二步和第三步进行重排序，顺序就成了：
	   *  a,分配内存空间
	   *  b,将对象指向刚分配的内存空间
	   *  c,初始化对象
	   *  
	   *  现在考虑重排序后，两个线程发生了以下调用：
		Time  |	Thread A				|	Thread B
		T1	检查到uniqueSingleton为空		|
		T2	获取锁						|
		T3	再次检查到uniqueSingleton为空	|
		T4	为uniqueSingleton分配内存空间	|
		T5	将uniqueSingleton指向内存空间	|
		T6								|	检查到uniqueSingleton不为空
		T7								|	访问uniqueSingleton（此时对象还未完成初始化）
		T8	初始化uniqueSingleton			|
		
		在这种情况下，T7时刻线程B对uniqueSingleton的访问，访问的是一个初始化未完成的对象。
		需要使用volatile关键字，重排序被禁止，所有的写（write）操作都将发生在读（read）操作之前
	 */
	private volatile static Singleton INSTANCE6;
    public static Singleton getInstance6() {
        if (INSTANCE6 == null) {
            synchronized (Singleton.class) {
                if (INSTANCE6 == null) {
                    INSTANCE6 = new Singleton();
                }
            }
        }
        return INSTANCE6;
    }
}
