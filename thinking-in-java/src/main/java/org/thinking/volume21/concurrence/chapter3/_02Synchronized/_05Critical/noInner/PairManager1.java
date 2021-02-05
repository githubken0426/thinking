package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical.noInner;

public class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		super.pair.incrementX();
		super.pair.incrementY();
		super.store(getPair());
	}

}
