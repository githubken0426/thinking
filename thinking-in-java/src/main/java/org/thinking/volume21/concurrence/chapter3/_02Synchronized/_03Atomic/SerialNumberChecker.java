package  org.thinking.volume21.concurrence.chapter3._02Synchronized._03Atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;


public class SerialNumberChecker {
	private static final int size=10;
	private static CircularSet serials=new CircularSet(1000);
	private static ExecutorService exe=Executors.newCachedThreadPool();
	
	static class SerialChecker implements Runnable{

		@Override
		public void run() {
			while(true){
				int serial=SerialNumberGenerator.nextSerialNumber();
				System.out.println(Thread.currentThread()+"--"+serial);
				if(serials.contains(serial)){
					System.out.println("Duplicate "+serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
		
	}
	
	public static void main(String[] args) {
		for(int i=0;i<size;i++){
			exe.execute(new SerialChecker());
		}
	}
}	
