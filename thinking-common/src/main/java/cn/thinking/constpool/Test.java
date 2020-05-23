package cn.thinking.constpool;

import java.nio.ByteBuffer;

public class Test {
    static int x = 10;
    static int b;

    static {
        x += 5;
    }

    static {
        x /= 3;
    }
    private static int i = 100;

    public static void main(String[] args) {
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 = Integer.valueOf(59);
        Integer i04 = new Integer(59);
        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
        Long l = 233l;
        byte[] bytesLong = longToBytes(l);
        System.out.println(bytesToLong(bytesLong));

        try {
            test(new int[]{0, 1, 2, 3, 4, 5});
        } catch (Exception e) {
            System.out.print("E");
        }
        System.out.println(x);
        boolean b = true ? false : true == true ? false : true;
        System.out.println(b);
        System.out.println(123.23 + 789.20);

        double a = .3 - .2;
        System.out.println(a);
        double y = .2 - .1;
        System.out.println(y);
        double x = (.1 + .1 + .1);
        System.out.println(x);
        System.out.println(x == .3);

    }

    private static void test(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i] % 2 == 0) {
                    throw new NullPointerException();
                } else {
                    System.out.print(i);
                }
            } finally {
                System.out.print("e");
            }
        }
    }

    /**
     * byte[] Long
     *
     * @param b
     * @return 2017-5-12 01:26:28
     */
    private static ByteBuffer buffer = ByteBuffer.allocate(8);

    public static Long bytesToLong(byte[] b) {
        buffer.put(b, 0, b.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    /**
     * byte[] String
     *
     * @param b
     * @return 2017-5-12 01:26:28
     */
    public static String test2(byte[] b) {
        return new String(b);
    }
}
