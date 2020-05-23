package cn.thinking.algorithm;

/**
 * 随机因子: 种子数只是随机算法的起源数字，和生成的随机数字的区间无关。
 *
 * @author ken
 */
public class RandomFactor {
    public static void main(String[] args) {
        Integer result[] = {20, 40, 11, 50, 10, 60, 5};
        System.out.println("排序前");
        testSort(result);
        System.out.println("\n排序后");
        //testSort(quickSort(result, 0, result.length - 1));
    }

    public static void testSort(Integer[] array) {
        for (int item : array) {
            System.out.print(item);
            System.out.print(",");
        }
    }

    public static Integer[] quickSort(Integer[] array, int left, int right) {
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
             * 10，20，50，40，60，至此完成一次排序。 第五步：此时20已经潜入到数组的内部，20的左侧一组数都比20小，20的右侧作为一组数都比20大，
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
