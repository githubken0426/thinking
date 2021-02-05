package  org.thinking.volume21.concurrence.chapter3._02Synchronized._03Atomic;

import java.util.Arrays;

/**
 * reuses storage so we do not out of memory
 * @author Administrator
 * 2016-5-3 ����10:52:23
 *
 */

public class CircularSet {
	
	private int array[];
	private int index;
	private int len;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for (int i = 0; i < size; i++)
			array[i] = -1;
	}

	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
//		System.out.println(Arrays.toString(array));
	}

	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++)
			if (array[i] == val)
				return true;
		return false;
	}
}
