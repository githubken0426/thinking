package cn.thinking.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListAccessTest {
	final static List<Integer> list = new ArrayList<Integer>();
	static{
		for (int i = 0; i < 10000000; i++)
			list.add(i);
	}
	
	public static void main(String[] args) {
		System.out.println(list.size());
		// foreach
		new Thread(new Runnable() {
			@Override
			public void run() {
				Long start = System.currentTimeMillis();
				for (Integer t : list) {
					Integer in = t;
				}
				Long end = System.currentTimeMillis();
				System.out.println("\nForeach complete:" + (end - start));
			}

		}).start();

		// for
		new Thread(new Runnable() {
			@Override
			public void run() {
				Long start = System.currentTimeMillis();
				for (int i = 0; i < list.size(); i++) {
					Integer in = list.get(i);
				}
				Long end = System.currentTimeMillis();
				System.out.println("\nFor complete:" + (end - start));
			}

		}).start();
		
		// iterator
		new Thread(new Runnable() {
			@Override
			public void run() {
				Iterator<Integer> it = list.iterator();
				Long start = System.currentTimeMillis();
				while (it.hasNext()) {
					Integer in = it.next();
				}
				Long end = System.currentTimeMillis();
				System.out.println("\nIteraotr complete:" + (end - start));
			}

		}).start();
	}

}
