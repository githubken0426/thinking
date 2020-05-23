package cn.thinking.signature.exception;

public class SignatureVerificationException extends RuntimeException {

    private static final long serialVersionUID = 6331324086823293998L;

    private String message;
    private Throwable cause;

    public SignatureVerificationException() {
        super();
    }

    public SignatureVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignatureVerificationException(String message) {
        super(message);
    }
}
