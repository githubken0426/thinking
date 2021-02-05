package  org.thinking.volume21.concurrence.chapter3._02Synchronized._05Critical.noInner;


public class PairChecker implements Runnable {

	private PairManager pm;
	public PairChecker(PairManager pm){
		this.pm=pm;
	}
	
	@Override
	public void run() {
		while(true){
			pm.atomic.incrementAndGet();
		}
	}

}
