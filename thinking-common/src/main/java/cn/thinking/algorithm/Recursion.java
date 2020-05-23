package cn.thinking.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 递归调用是一种特殊的嵌套调用，是某个函数调用自己或者是调用其他函数后再次调用自己
 * 在递归和循环之间选择时，应该优先选择的是循环而非递归，特别是要避免深度的递归
 * 尾递归调用：指的是一个方法或者函数的调用在另一个方法或者函数的最后一条指令中进行。
 * 递归的三个条件：
 * 	边界条件
 * 	递归前进段
 * 	递归返回段
 * 当边界条件不满足时，递归前进；当边界条件满足时，递归返回。
 *
 * 递归缺点：
 * 但递归是用栈机制实现的，每深入一层，都要占去一块栈数据区域，对嵌套层数深的一些算法，递归会力不从心，空间上会以内存崩溃而告终，
 * 而且递归也带来了大量的函数调用，这也有许多额外的时间开销。所以在深度大时，它的时空性就不好了。（会占用大量的内存空间）
 * @ClassName: Recursion 
 * @Description: 
 * @author ken 
 * @date 2018年11月27日 上午11:47:58
 */
public class Recursion {
	static int recursion(int n) {
		if (n == 0)// 递归边界
			return 1;
		return n * recursion(n - 1);// 递归公式
	}

	public static void main(String[] args) {
		System.out.println(recursion(5));
	}
}
