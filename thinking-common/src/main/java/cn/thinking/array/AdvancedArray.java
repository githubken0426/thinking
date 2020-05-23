package cn.thinking.array;

import java.util.Arrays;

/**
 * @Auther: ken.wangTJ
 * @Date: 5/20/2019 14:54
 * @Description:
 */
public class AdvancedArray {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[5];
        arr2 = arr1;
        for (int i = 0; i < arr2.length; i++)
            System.out.print(arr2[i] + " ");

        System.out.println();
        advancedTest();
        reflectArray(arr1);
        System.out.println("\nArrays.copyOf():");
        ArrayCopy.advancedArrayCopy(new int[]{3,10,49,30});

        System.out.println("\nArrays.copyOfRange():");
        ArrayCopy.advancedArrayCopyRange(new int[]{3,10,49,30});

        System.out.println("\nSystem.arraycopy():");
        ArrayCopy.advancedSystemArraycopy(arr1,new int[]{3,10,49,30,23,67,44});

        System.out.println("\narray.clone():");
        ArrayCopy.advancedArrayClone(new int[]{3,10,49,30,23,67,44});
    }

    public static void advancedTest() {
        int[] array_00 = new int[10];
        System.out.println("array的父类是：" + array_00.getClass().getSuperclass());
        System.out.println("一维数组：" + array_00.getClass().getName());

        int[][] array_01 = new int[10][10];
        System.out.println("二维数组：" + array_01.getClass().getName());

        int[][][] array_02 = new int[10][10][10];
        System.out.println("三维数组：" + array_02.getClass().getName());
    }

    public static void reflectArray(int[] array) {
        System.out.println("reflectArray:");
        Class clazz = array.getClass();
        System.out.println("DeclaredFields:" + clazz.getDeclaredFields().length);
        System.out.println("DeclaredMethods:" + clazz.getDeclaredMethods().length);
        System.out.println("DeclaredConstructors:" + clazz.getDeclaredConstructors().length);
        System.out.println("DeclaredAnnotations:" + clazz.getDeclaredAnnotations().length);
        System.out.println("DeclaredClasses:" + clazz.getDeclaredClasses().length);
        System.out.println("Superclass:" + clazz.getSuperclass());
    }

    /**
     * JVM array.length:
     * 0 iconst_2         //将int型常量2压入操作数栈
     * 1 newarray 10 (int) //将2弹出操作数栈，作为长度，创建一个元素类型为int, 维度为1的数组，并将数组的引用压入操作数栈
     * 3 astore_1          //将数组的引用从操作数栈中弹出，保存在索引为1的局部变量(即a)中
     * 4 aload_1           //将索引为1的局部变量(即a)压入操作数栈
     * 5 arraylength       //从操作数栈弹出数组引用(即a)，并获取其长度(JVM负责实现如何获取)，并将长度压入操作数栈
     * 6 istore_2          //将数组长度从操作数栈弹出，保存在索引为2的局部变量(即i)中
     * 7 return            //main方法返回
     */
    public void getArrayLength() {
        int a[] = new int[2];
        int i = a.length;
    }


    static class ArrayCopy{
        /**
         * 目标数组如果已经存在，将会被重构
         * @param sources
         */
        public static void advancedArrayCopy(int[] sources) {
            int[] newScores = (int[]) Arrays.copyOf(sources, sources.length);
            for (int i = 0; i < newScores.length; i++)
                System.out.print(newScores[i] + " ");
        }

        public static void advancedArrayCopyRange(int[] sources) {
            int[] newScores = (int[]) Arrays.copyOfRange(sources,0, sources.length);
            for (int i = 0; i < newScores.length; i++)
                System.out.print(newScores[i] + " ");
        }
        //目标数组必须已经存在，且不会被重构，替换目标数组中的部分元素.
        public static void advancedSystemArraycopy(int[] arr1,int[] arr2) {
            //System.arraycopy(来源, 起始索引, 目的, 起始索引, 复制长度);
            System.arraycopy(arr1, 0, arr2, 0, arr1.length);
            for (int i = 0; i < arr2.length; i++)
                System.out.print(arr2[i] + " ");
        }

        public static void advancedArrayClone(int[] sources) {
            int[] newScores = (int[]) sources.clone();
            for (int i = 0; i < newScores.length; i++)
                System.out.print(newScores[i] + " ");
        }
    }
}
