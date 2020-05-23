package cn.thinking.concurrent.factory;

import java.util.concurrent.ThreadFactory;

/**
 * 后台线程工具类
 * 也可以创建优先级，线程名称等属性的工具类
 * @author Administrator
 * 2016-1-19 下午04:38:04
 *
 */
public class DeamonThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t=new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
