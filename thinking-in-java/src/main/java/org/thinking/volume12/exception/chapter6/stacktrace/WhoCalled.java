package org.thinking.volume12.exception.chapter6.stacktrace;

/**
 * getStackTrace()返回由栈轨迹构成的数组，元素0（栈顶）是调用程序的最后一个方法调用。
 * @author kun.f.wang
 *
 */
public class WhoCalled {
	public static void main(String[] args) {
		f();
		System.out.println("------------------");
		g();
		System.out.println("------------------");
		h();
	}

	private static void f() {
		try {
			throw new Exception();
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace())
				System.out.println(ele);
		}
	}

	private static void g() {
		f();
	}

	private static void h() {
		g();
	}

}
