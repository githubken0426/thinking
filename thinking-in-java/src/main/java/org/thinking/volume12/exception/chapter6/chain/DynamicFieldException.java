package org.thinking.volume12.exception.chapter6.chain;

/**
 * 异常链：在捕获一个异常后抛出另一个异常，并且希望把原始异常信息保存下来，这就是异常链。
 * 
 * Throwable可以通过构造器接受cause参数来追踪原始异常。这个cause就是异常原由，代表着原始异常，
 * 即使在当前位置创建并抛出行的异常，也可以通过这个cause追踪到异常最初发生的位置。
 * 
 * 只有Error,Exception,RuntimeException提供了带cause参数的构造器，其他的所以异常就只有通过initCause()来设置cause。
 * 
 * @ClassName: DynamicFieldException
 * @author ken
 * @date 2018-8-31 下午3:15:58
 */
public class DynamicFieldException extends Exception {
	private static final long serialVersionUID = -6981552260165798605L;
}
