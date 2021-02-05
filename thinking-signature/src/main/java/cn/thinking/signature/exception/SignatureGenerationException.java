package cn.thinking.signature.exception;

public class SignatureGenerationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SignatureGenerationException() {
		super();
	}

	public SignatureGenerationException(String message) {
		super(message);
	}

	public SignatureGenerationException(String message, Throwable cause) {
		super(message, cause);
	}
}
