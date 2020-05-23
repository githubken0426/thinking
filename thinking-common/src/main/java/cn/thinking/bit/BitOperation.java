package cn.thinking.bit;

import cn.thinking.map.MapKey;

public class BitOperation {
	/**
	 * 1、乘法交换律：在两个数的乘法运算中，在从左往右计算的顺序，两个因数相乘，交换因数的位置，积不变。
	 * 	乘法交换律公式：a×b=b×a
	 * 
	 * 2、乘法结合律：三个数相乘，先把前两个数相乘，再和另外一个数相乘，或先把后两个数相乘，再和另外一个数相乘，积不变。
	 * 	乘法结合律公式(a×b)×c=a×(b×c)
	 * 
	 * 3、乘法分配律：两个数的和与一个数相乘，可以先把它们分别与这个数相乘，再将积相加。
	 * 	乘法分配律公式：(a+b)×c=a×c+b×c
	 */
	/**
	 * 计算机要使用一定的编码方式进行存储. 原码, 反码, 补码是机器存储一个具体数字的编码方式。
	 * Java中int 是4个字节，所以高位需要补0，占32位，最高位1代表负
	 * 反码，符号位不变，其余各个位取反。补码在反码的基础上+1
	 * 
	 * 真值（-5）：1000 0000 0000 0000 0000 0000 0000 0101
	 * 反码（-5）：1111 1111 1111 1111 1111 1111 1111 1010
	 * 补码（-5）：1111 1111 1111 1111 1111 1111 1111 1011
	 * 真值（3）: 0000 0000 0000 0000 0000 0000 0000 0011
	 * 
	 * 移位：如果是负数，等移位完成以后，然后保持符号位不变，其余按位取反加1，得到移位后所对应数的原码
	 */
	final static int numA = 3, numB = -5, shift = 1;
	
	public static void main(String[] args) {
		System.out.println(MapKey.numberOfLeadingZeros(15));
		
		System.out.println("hash(test):" + hash("test"));
		System.out.println("3:" + Integer.toBinaryString(numA));
		System.out.println("-5:" + Integer.toBinaryString(numB));
		andOperation(numA, numB);
		orOperation(numA, numB);
		inverseOperation(numA);
		xorOperation(numA, numB);
		
		shiftLeft(numB);
		shiftRight(numB);
		shiftRightWithSign(numB);
	}

	
	/**
	 * 位与运算（&）：两位同时为1，结果才为1，否则为0 （位与操作满足交换律和结合律，甚至分配律）
	 * 运算规则：0&0=0; 0&1=0; 1&0=0; 1&1=1;
	 * 
	 * 另，负数按补码形式参加按位与运算。 “与运算”的特殊用途：
	 * （1）清零。如果想将一个单元清零，即使其全部二进制位为0，只要与一个各位都为零的数值相与，结果为零。
	 * （2）取一个数中指定位.方法：找一个数，对应X要取的位，该数的对应位为1，其余位为零，此数与X进行“与运算”可以得到X中的指定位。
	 * 例：设X=10101110，取X的低4位，用 X & 0000 1111 = 0000 1110 即可得到； 还可用来取X的2、4、6位。
	 */
	public static void andOperation(int numA, int numB) {
		System.out.println("与(&):" + (numA & numB));
	}

	/**
	 * 或运算（|）：两位同时为0，结果才为0，否则为1 （位与操作满足交换律和结合律，甚至分配律）
	 * 运算规则：0|0=0; 0|1=1; 1|0=1; 1|1=1;
	 */
	public static void orOperation(int numA, int numB) {
		System.out.println("或(|):" + (numA | numB));
	}
	/**
	 * 非运算（~）：即按位取反，1 变 0，0 变 1
	 * 运算规则：0|0=0; 0|1=1; 1|0=1; 1|1=1;
	 * @param num
	 */
	public static void inverseOperation(int num) {
		System.out.println("非(~):" + (~num));
	}
	/**
	 * 异或运算(^):两个比特位相同（都为 0 或都为 1）为 0，相异为 1（异或操作满足交换律和结合律，甚至分配律。任何整数和自己异或的结果为 0，任何整数与 0 异或值不变）
	 */
	public static void xorOperation(int numA, int numB) {
		System.out.println("异或(^):" + (numA ^ numB));
	}
	
	/**
	 * 左移(<<):将所有的二进制位按值向←左移动若干位，左移操作与正负数无关，高位舍弃，低位补 0
	 * @param num
	 */
	public static void shiftLeft(int num){
		System.out.println("左移(<<):" + (num << shift));
		System.out.println("左移-前(<<):" + Integer.toBinaryString(num));
		System.out.println("左移-后(<<):" + Integer.toBinaryString(num << shift));
	}
	
	/**
	 * 右移(>>):"有符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。
	 * 使用符号扩展机制，也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1.
	 * 
	 * @param num
	 */
	public static void shiftRightWithSign(int num){
		System.out.println("有符号右移(>>):" + (num >> shift));
		System.out.println("有符号右移-前(>>):" + Integer.toBinaryString(num));
		System.out.println("有符号右移-后(>>):" + Integer.toBinaryString(num >> shift));
	}
	
	/**
	 * 右移(>>>):无符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。
	 * 采用0扩展机制，也就是说，无论值的正负，都在高位补0.
	 * @param num
	 */
	public static void shiftRight(int num){
		System.out.println("无符号右移(>>>):" + (num >>> shift));
		System.out.println("无符号右移-前(>>>):" + Integer.toBinaryString(num));
		System.out.println("无符号右移-后(>>>):" + Integer.toBinaryString(num >>> shift));
	}
	
	// (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
	static final int hash(Object key) {
		int h = key.hashCode();
		int result = h >>> 16;
		int res = h ^ result;
		return (key == null) ? 0 : res;
	}

}
