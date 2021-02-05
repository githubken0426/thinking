package cn.thinking.signature.exception;

public class SecurityKeyInitializationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityKeyInitializationException() {
		super();
	}

	public SecurityKeyInitializationException(String message) {
		super(message);
	}

	public SecurityKeyInitializationException(String message, Throwable cause) {
		super(message, cause);
	}
}
