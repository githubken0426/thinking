package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical.noInner;


public class PairManager2 extends PairManager {

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
