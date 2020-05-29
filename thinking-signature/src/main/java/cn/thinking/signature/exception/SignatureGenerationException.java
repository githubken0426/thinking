package cn.thinking.signature.exception;

public class SignatureGenerationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
    private Throwable cause;

    public SignatureGenerationException() {
        super();
    }

    public SignatureGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
