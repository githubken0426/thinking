package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �ٽ�����Critical section����ָ����һ�����ʹ�����Դ�����磺�����豸���ǹ��ô洢�����ĳ���Ƭ�Σ�
 * ����Щ������Դ���޷�ͬʱ������̷߳��ʵ����ԡ���ʹ��synchronized����
 * �����߳̽����ٽ�����ʱ�������̻߳��ǽ��̱���ȴ������磺bounded waiting �ȴ�������
 * ��һЩͬ���Ļ��Ʊ������ٽ����εĽ�������뿪��ʵ�֣�
 * ��ȷ����Щ������Դ�Ǳ�������ʹ��
 * 
 * ͬ����һ�ָ�Ϊ���ӵĻ��⣬��������һ�������ͬ��
 * 
 * ���̽����ٽ����ĵ���ԭ���ǣ�
 * 1����������ɽ���Ҫ�������е��ٽ�����һ�ν�����һ�����̽��롣
 * 2���κ�ʱ�򣬴����ٽ����ڵĽ��̲��ɶ���һ���������н��̽����Լ����ٽ�����
 * 	  ������������ͼ�����ٽ����Ľ��̱���ȴ���
 * 3�������ٽ����Ľ���Ҫ������ʱ�����˳����Ա����������ܼ�ʱ�����Լ����ٽ�����
 * 4��������̲��ܽ����Լ����ٽ�������Ӧ�ó�CPU��������̳��֡�æ�ȡ�����
 * @author Administrator
 *
 */
public class Pair {//�̲߳��ǰ�ȫ��
	private int x,y;
	public Pair(){
		this(0,0);
	}
	public Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x++;
	}
	public int getY() {
		return y++;
	}
	
	public void incrementX(){++x;}
	
	public void incrementY(){++y;}
	
	public String toString(){
		return "X:"+x+",Y:"+y;
	}
	
	public class PariValuesNotEuqalException extends RuntimeException{
		public PariValuesNotEuqalException(){
			super("Pair not equals:"+Pair.this);
		}
	}
	//���ⲻ����--���б����������
	public void checkState(){
		if(x!=y)
			throw new PariValuesNotEuqalException();
	}
	
	/**
	 * ģ�����ģʽ
	 * @author Administrator
	 * 2016-5-3 ����02:47:48
	 *
	 */
	//Pair�ڴ����н����̰߳�ȫ��
	 abstract class PairManager{
		
		AtomicInteger atomic=new AtomicInteger();
		protected Pair pair=new Pair();
		private List<Pair> storage=Collections.synchronizedList(new ArrayList<Pair>());
		
		//����һ��ԭʼ�ġ��̰߳�ȫ��Pair
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
		/**
		 * ģ�巽��
		 */
		public abstract void increment();
	}
	//1��ͬ����������
	 class PairManager1 extends PairManager{
		@Override
		public synchronized void increment() {
			super.pair.incrementX();
			super.pair.incrementY();
			super.store(getPair());
		}
	}
	/**
	 * 2���ٽ���-->synchronized��
	 * ���߳����е��ô�����ڣ��ͻ�ӵ��Object����Ķ�������
	 * �������̹߳���ͬһ��Object������ô��ʱ�ͻ��γɻ��⣡
	 * �ر�ģ���obj == thisʱ����ʾ��ǰ���ø÷�����ʵ������
	 * 
	 * ʹ��synchronized�飬PairManager2���PairManager1������ʱ�����
	 * Ч�ʻ�ߣ���Ҳ����Ըʹ��synchronized���ԭ��ʹ�����̻߳�ȡ����ķ��ʻ���
	 * @author Administrator
	 *
	 */
	 class PairManager2 extends PairManager{
		@Override
		public void increment() {
			Pair temp;
			synchronized(this){
				super.pair.incrementX();
				super.pair.incrementY();
				temp=getPair();
			}
			super.store(temp);//store�����Ѿ����̰߳�ȫ�ģ����Բ��ü���
		}
	}
	
	static class PairManipulator implements Runnable{
		private PairManager pm;
		public PairManipulator(PairManager pm){
			this.pm=pm;
		}
		
		@Override
		public void run() {
			while(true)
				pm.increment();
		}
		
		public String toString(){
			return "Pair:"+pm.getPair()+",CheckerCount:"+pm.atomic.get();
		}
	}
	
	static class PairChecker implements Runnable{
		private PairManager pm;
		public PairChecker(PairManager pm){
			this.pm=pm;
		}
		
		@Override
		public void run() {
			while(true){
				pm.atomic.incrementAndGet();
				pm.getPair().checkState();
			}
		}
	}
	
	//ʹ��Lock���󴴽��ٽ���
	 class ExplicitPairManager1 extends PairManager{
		private Lock lock=new ReentrantLock();
		
		@Override
		public synchronized void increment() {
			lock.lock();//�Ѿ�lock��Ϊ�λ�Ҫsynchronized
			try {
				pair.incrementX();
				pair.incrementY();
				store(getPair());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}
	
	 class ExplicitPairManager2 extends PairManager{
		private Lock lock=new ReentrantLock();
		@Override
		public void increment() {
			Pair temp = null ;
			lock.lock();
			try {
				pair.incrementX();
				pair.incrementY();
				temp=getPair();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			store(temp);
		}
	}
}
