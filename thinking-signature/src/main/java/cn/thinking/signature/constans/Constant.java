package cn.thinking.signature.constans;

public class Constant {
    /**
     * 身份验证
     */
    public static final String AUTHORIZATION = "Authorization";
    public static final String HEADER_SIGNATURE = "Head-Signature";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String HASH_ALGORITHM = "SHA-256";
    public static final String CONTENT_ENCODE = "UTF-8";
    public static final String RSA_ALGORITHM_KEY = "RSA";

    public static final String LOG_PREFIX = "SIGNATURE|Interceptor";
    public static final String SIGN_FAIL="Signature signing failed.";
    public static final String SIGN_VERIFICATION_FAIL="Signature verification failed.";

    public static final String CONFIGURE_PREFIX="signature";
}
