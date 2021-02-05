package org.thinking.volume12.exception.chapter4;

import org.thinking.volume12.exception.chapter1.MessageException;

public class ParcelLogger2 {
	public static void f() throws MessageException {
		System.out.println("thwor MyExcepton2:");
		throw new MessageException();
	}

	public static void g() throws MessageException {
		System.out.println("thwor MyExcepton2:");
		throw new MessageException("method g()");
	}

	public static void h() throws MessageException {
		System.out.println("thwor MyExcepton2:");
		throw new MessageException("method h()", 4);
	}

	public static void main(String[] args) {
		try {
			f();
		} catch (MessageException e) {
			e.printStackTrace(System.out);
		}

		try {
			g();
		} catch (MessageException e) {
			e.printStackTrace(System.out);
		}
		try {
			h();
		} catch (MessageException e) {
			e.printStackTrace(System.out);
			System.out.println("e.val():" + e.value());
		}
	}

}
