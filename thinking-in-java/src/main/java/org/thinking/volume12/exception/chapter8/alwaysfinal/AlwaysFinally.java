package org.thinking.volume12.exception.chapter8.alwaysfinal;

import org.thinking.volume12.exception.ExceptionFir;

public class AlwaysFinally {
	/**
	 * 	异常在没有被当前异常处理捕获的情况下，异常处理机制会在跳到更高一级异常处理之前调用finally
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Entering first try block!");
			try {
				System.out.println("Entering second try block!");
				throw new ExceptionFir("异常测试");
			} finally {
				System.out.println("finally in 2nd try block!");
			}
		} catch (ExceptionFir e) {
			e.printStackTrace();
			System.out.println("catch in 1st try block!");
		} finally {
			System.out.println("finally in 1st try block!");
		}
	}
}
