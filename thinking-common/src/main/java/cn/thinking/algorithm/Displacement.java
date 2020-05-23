package cn.thinking.algorithm;

/**
 * 移位运算符
 * <p>
 * Java三种移位运算符:
 * <<:左移运算符，num << 1,相当于num乘以2
 * >>:右移运算符，num >> 1,相当于num除以2
 * >>>:无符号右移，忽略符号位，空位都以0补齐
 * <p>
 * 按二进制形式把所有的数字向左移动对应的位数，
 * 高位(最左点位)移出(舍弃)，低位(最右边位)的空位补零(有符号则正数补零，负数补1)。
 *
 * @author ken
 * 2017-5-31 下午04:23:58
 */
public class Displacement {

    public static void main(String[] args) {
        int number = 10;
        // 原始数二进制
        System.out.println("原始数二进制:" + printInfo(number));
        // 左移一位
        number = number << 1;
        System.out.println("左移一位:" + printInfo(number) + "\n");

        // 右移2位
        number = -10;
        number = number >> 2;
        System.out.println("右移2位:" + printInfo(number));
        //有符号右移二进制转十进制：减一取反
        System.out.println(Integer.parseInt(String.valueOf(number)) + "\n");

        // 无符号右移2位。负二进制(原码取反=反码，反码+1=补码)
        number = 5;
        System.out.println("原始数二进制:" + printInfo(number));
        number = number >>> 1;
        System.out.println("无符号右移1位:" + printInfo(number));
        System.out.println(Integer.parseInt(String.valueOf(number)));
    }

    public static String printInfo(int num) {
        return Integer.toBinaryString(num);
    }
}
