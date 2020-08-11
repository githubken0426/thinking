package cn.thinking.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterGuava {

	public static void main(String[] args) {
		guavaTest();
	}

	public static void guavaTest() {
		long star = System.currentTimeMillis();
		BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10000000, 0.01);
		for (int i = 0; i < 10000000; i++) {
			filter.put(i);
		}
		System.out.println("put：" + (System.currentTimeMillis()-star));
		System.err.println(filter.mightContain(1));
		System.err.println(filter.mightContain(2));
		System.err.println(filter.mightContain(2));
		System.err.println(filter.mightContain(10000000));
		long end = System.currentTimeMillis();
		System.out.println("执行时间：" + (end - star));
	}
}
