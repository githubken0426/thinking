package org.thinking.volume12.exception.chapter6.chain;

public class ExceptionChain {
	public static void main(String[] args) {
		try {
			f();
		} catch (SecondException e) {
			e.printStackTrace();
		}
	}

	public static void f() throws SecondException {
		try {
			g();
		} catch (FirstException e) {
			e.printStackTrace(System.out);
			/**
			 * 将Exception当作cause传递进去，这样能够获取FirstException的信息，形成一条异常链。
			 */
			throw new SecondException(e);
		}
	}

	public static void g() throws FirstException {
		throw new FirstException();
	}

}

class FirstException extends Exception {
	private static final long serialVersionUID = 1L;
}

class SecondException extends Exception {
	private static final long serialVersionUID = 1L;

	SecondException(Throwable throwable) {
		super(throwable);
	}

	SecondException() {
		super();
	}
}
