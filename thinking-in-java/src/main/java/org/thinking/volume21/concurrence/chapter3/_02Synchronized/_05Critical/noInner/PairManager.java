package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical.noInner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class PairManager {
	AtomicInteger atomic=new AtomicInteger();
	protected Pair pair=new Pair();
	private List<Pair> storage=Collections.synchronizedList(new ArrayList<Pair>());
	
	//����һ��ԭʼ�̰߳�ȫ��Pair
	public synchronized Pair getPair(){
		return new Pair(pair.getX(),pair.getY());
	}
	//����һ����ʱ�Ĳ���
	protected void store(Pair p){
		storage.add(p);
		try {
			TimeUnit.MICROSECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void increment();
}
