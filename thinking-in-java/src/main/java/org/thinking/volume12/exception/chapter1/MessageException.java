package org.thinking.volume12.exception.chapter1;

public class MessageException extends Exception {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			throw new MessageException("Test MessageException");
		} catch (MessageException e) {
			e.printStackTrace();

			System.out.println(e.getLocalizedMessage());
		}
	}

	private int x;

	public MessageException() {
	}

	public MessageException(String msg) {
		super(msg);
	}

	public MessageException(String msg, int x) {
		super(msg);
		this.x = x;
	}

	public int value() {
		return x;
	}

	/**
	 * 重写Throwable的getMessage()方法
	 */
	public String getMessage() {
		return "Detail Message : " + super.getMessage();
	}

	/**
	 * 继承类应该override(覆盖)getLocalizedMessage() 此方法来提供一个针对地区方言的错误信息
	 * getLocalizedMessage()默认掉用getMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
