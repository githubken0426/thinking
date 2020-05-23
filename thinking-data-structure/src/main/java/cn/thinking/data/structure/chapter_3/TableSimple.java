package cn.thinking.data.structure.chapter_3;

/**
 * @Auther: ken.wangTJ
 * @Date: 3/21/2019 10:29
 * @Description:
 */
public class TableSimple {
    public static void main(String[] args) {

    }

    /**
     * 在0的位置插入，则所有元素需要后移
     * 在0的位置删除，所有元素需要前移
     * 因此最坏情况是O(N).
     * 如果新增删除发生在表的高端，则只花费O(1)
     * @param t
     * @return
     */
    private int[] initTable(int[] t) {
        int[] newArr = new int[t.length * 2];
        for (int i = 0; i < t.length; i++) {
            newArr[i] = t[i];
        }
        t = newArr;
        return t;
    }
}
