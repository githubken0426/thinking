package cn.thinking.collection;

import java.util.ArrayList;

public class ListCapacityTest {
    public static void main(String[] args) {
        final int size = 10000000 / 2;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < size; i++)
                    list.add(i);
                Long end = System.currentTimeMillis();
                System.out.println("\nNonCapacity:" + (end - start));
            }

        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Long start = System.currentTimeMillis();
                ArrayList<Integer> list = new ArrayList<Integer>();
                //可以看出设置ensureCapacity可以提高创建ArrayList效率
                list.ensureCapacity(size);
                for (int i = 0; i < size; i++)
                    list.add(i);
                Long end = System.currentTimeMillis();
                System.out.println("\nEnsuerCapacity:" + (end - start));
            }
        }).start();
    }

}
