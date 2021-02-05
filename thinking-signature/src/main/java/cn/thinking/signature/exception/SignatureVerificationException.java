package cn.thinking.signature.exception;

public class SignatureVerificationException extends RuntimeException {
	private static final long serialVersionUID = 6331324086823293998L;

	public SignatureVerificationException() {
		super();
	}

	public SignatureVerificationException(String message) {
		super(message);
	}

	public SignatureVerificationException(String message, Throwable cause) {
		super(message, cause);
	}
}
