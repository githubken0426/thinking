package cn.thinking.algorithm;

public class ExchangeSort {

	static class Bubble {
		/**
		 * 交换排序 一： 冒泡排序 算法思想 原理是临近的数字两两进行比较,按照从小到大或者从大到小的顺序进行交换,这样一趟过去后,
		 * 最大或最小的数字被交换到了最后一位,然后再从头开始进行两两比较交换,直到倒数第二位时结束；
		 */
		public static int[] bubbleSort(int[] array) {
			int temp = 0;
			for (int i = array.length - 1; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					if (array[i] < array[j]) {
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
			return array;
		}

		/**
		 * 设置标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可
		 * 
		 * @param array
		 * @param n
		 */
		public static Integer[] bubbleSort_2(Integer array[]) {
			int i = array.length - 1; // 初始时,最后位置保持不变
			while (i > 0) {
				int pos = 0; // 每趟开始时,无记录交换
				for (int j = 0; j < i; j++)
					if (array[j] > array[j + 1]) {
						pos = j; // 记录交换的位置
						int tmp = array[j];
						array[j] = array[j + 1];
						array[j + 1] = tmp;
					}
				i = pos; // 为下一趟排序作准备
			}
			return array;
		}
	}

	/**
	 * 交换排序 二：要求时间最快时。 
	 * 选择第一个数为a，小于a的数放在左边，大于a的数放在右边,递归的将a左边和右边的数都按照第一步进行，直到不能递归.
	 * 
	 * 快速排序 算法思想 通过一趟排序将要排序的数据分割成独立的两部分, 其中一部分的所有数据都比另外一部分的所有数据都要小,
	 * 然后再按此方法对这两部分数据分别进行快速排序, 整个排序过程可以递归进行，以此达到整个数据变成有序序列；
	 */
	public static class QuickSort {
		public static int[] quickSort(int[] array, int left, int right) {
			int start = left;
			int end = right;
			// 第一步：首先我们从数组的left位置取出基准数。
			int key = array[left];
			while (end > start) {
				/**
				 * 第二步：从数组的right位置向前找，一直找到比（key）小的数，如果找到，将此数赋给left位置（也就是将10赋给20）.
				 * 此时数组为：10，40，50，10，60，left和right指针分别为前后的10。
				 */
				while (end > start && array[end] >= key)
					end--;
				if (array[end] <= key) {
					int temp = array[end];
					array[end] = array[start];
					array[start] = temp;
				}
				/**
				 * 第三步：从数组的left位置向后找，一直找到比（key）大的数， 如果找到，将此数赋给right的位置（也就是40赋给right侧10）,
				 * 此时数组为：10，40，50，10，60，left和right指针分别为前后的40。
				 */
				while (end > start && array[start] <= key)
					start++;
				if (array[start] >= key) {
					int temp = array[start];
					array[start] = array[end];
					array[end] = temp;
				}
				/**
				 * 第四步：重复“第二,第三“步骤，直到left和right指针重合，最后将（base）插入到40的位置， 此时数组值为：
				 * 10，20，50，40，60，至此完成一次排序。
				 * 第五步：此时20已经潜入到数组的内部，20的左侧一组数都比20小，20的右侧作为一组数都比20大，
				 * 以20为切入点对左右两边数按照"第一，第二，第三，第四"步骤进行，最终快排大功告成。
				 */
			}
			// 左边序列。第一个索引位置到关键值索引-1
			if (start > left)
				quickSort(array, left, start - 1);
			// 右边序列。从关键值索引+1到最后一个
			if (end < right)
				quickSort(array, end + 1, right);
			return array;
		}
	}
}
