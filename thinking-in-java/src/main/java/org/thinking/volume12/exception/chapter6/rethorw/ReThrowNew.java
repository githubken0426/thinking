package org.thinking.volume12.exception.chapter6.rethorw;

import org.thinking.volume12.exception.ExceptionFir;
import org.thinking.volume12.exception.ExceptionSec;

public class ReThrowNew {
	public static void f() throws ExceptionFir {
		throw new ExceptionFir("throw from f()");
	}

	/**
	 * 捕获异常后再次抛出，效果类似于fillInStackTrace(),原来的异常点将丢失。 最后那个异常只是知道main，对f()一无所知
	 * Exception都是new在堆上的对象。垃圾回收器会自动清理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			try {
				f();
			} catch (ExceptionFir e) {
				e.printStackTrace(System.out);
				throw new ExceptionSec("throw from Exception2ND");
			}
		} catch (ExceptionSec e) {
			e.printStackTrace(System.err);
		}
	}
}
