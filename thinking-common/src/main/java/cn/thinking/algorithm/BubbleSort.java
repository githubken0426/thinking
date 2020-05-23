package cn.thinking.algorithm;


public class BubbleSort {
	
	/**
	 * 冒泡排序
	 * @ClassName: BubbleSort
	 * @Description:
	 * @author ken
	 * @date 2018年11月8日 上午8:40:23
	 */
	public static int[] bubbleSort(int[] t) {
		int temp = 0;
		for (int i = t.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (t[i] < t[j]) {
					temp = t[i];
					t[i] = t[j];
					t[j] = temp;
				}
			}
		}
		return t;
	}
	public static void main(String[] args) {
		int a[] = new int[] { 8, 12, 13, 3, 4, 7, 9, 10 };
		int t[]=bubbleSort(a);
		for (int i : t) {
			System.out.print(i+",");
		}
	}
}
