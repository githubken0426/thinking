package cn.thinking.algorithm;

import java.util.Arrays;

/**
 * 八大经基本排序算法
 * 
 * @author Administrator 2016-4-8 上午09:41:19
 * 
 */
public class Algorithm {
	private static int a[] = new int[] { 8, 12, 13, 3, 4, 7, 9, 10,1 };
	public static void main(String[] args) {
		System.out.println("=======排序前==========");
		System.out.println(Arrays.toString(a));
		//insertSort();
		// shellSort();
		// bubbleSort();
		ExchangeSort.QuickSort.quickSort(a, 0, a.length-1);
		System.out.println();
		System.out.println("=======排序后==========");
		System.out.println(Arrays.toString(a));
	}

	
	/**
	 * 插入排序 一：直接插入排序 算法思想： 将一个待排序的纪录，按其关键码值的大小插入前面已经排序的文件中适当位置上， 直到全部插入完为止
	 * 
	 */
	public static void insertSort() {
		// 方法一
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j = i - 1;
			for (; j >= 0 && temp < a[j]; j--) {
				// 将大于temp的值整体后移一个单位
				a[j + 1] = a[j];
			}
			a[j + 1] = temp;
		}
	}

	public static void insertSort2() {
		// 方法二
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					int temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
				}
			}
		}
	}

	public static void insertSort3() {
		// 方法三
		int tmp;
		int j;
		for (int i = 1; i < a.length; i++) {
			tmp = a[i];
			for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
				a[j + 1] = a[j];
			}
			a[j + 1] = tmp;
		}
	}

	/**
	 * 插入排序 二：希尔排序（最小增量排序）
	 */
	public static void shellSort() {

	}

	/**
	 * 选择排序 一：简单选择排序
	 */

	/**
	 * 选择排序 二：堆排序
	 */

	/**
	 * 归并排序
	 */

	/**
	 * 基数排序
	 */
}
