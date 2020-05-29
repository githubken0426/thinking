package cn.thinking.signature.exception;

public class SecurityKeyInitializationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    private Throwable cause;

    public SecurityKeyInitializationException(String s) {
        super();
    }

    public SecurityKeyInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
