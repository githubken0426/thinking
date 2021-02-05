package org.thinking.volume12.exception.chapter1;

import java.io.PrintStream;

public class FullConstructor {

	/**
	 * System.err 不会重定向，异常信息更容易看到。 System.out 会被重定向
	 * 
	 * @param args
	 * @throws @date 2018年8月31日 下午2:00:54
	 */
	public static void main(String[] args) {
		// printStackTrace(System.err):信息会输出到标准错误流
		print(System.err);

		print(System.out);
	}

	private static void print(PrintStream stream) {
		try {
			FullConstructor.f();
		} catch (MyException e) {
			e.printStackTrace(stream);
		}
	}

	private static void f() throws MyException {
		throw new MyException();
	}

}
