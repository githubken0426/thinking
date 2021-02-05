package org.thinking.volume12.exception.chapter8.lost;

public class ExceptionSilencer {

	@SuppressWarnings("finally")
	public static void main(String[] args) {
		try {
			throw new RuntimeException();
		} finally {
			/**
			 * 使用return后，异常将不再被抛出
			 */
			return;
		}
	}
}
