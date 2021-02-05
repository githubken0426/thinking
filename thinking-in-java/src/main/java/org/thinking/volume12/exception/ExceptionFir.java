package org.thinking.volume12.exception;

/**
 *	 异常的基本概念是用名称代表发生的问题，所以异常的名称可以望文知意
 * 
 * @author kun.f.wang
 */
public class ExceptionFir extends Exception {
	private static final long serialVersionUID = 1L;

	public ExceptionFir() {
	}

	public ExceptionFir(String msg) {
		super(msg);
	}
}
