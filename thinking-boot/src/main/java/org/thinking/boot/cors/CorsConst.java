package org.thinking.boot.cors;

public class CorsConst {
	/**
	 * 1、在Http 1.0及之前版本中，content-length字段可有可无。
	 * 2、在http1.1及之后版本。如果是keep alive，则content-length和chunk必然是二选一。
	 * 	若是非keep alive，则和http1.0一样。content-length可有可无
	 * 
	 * Content-Length长度设置过小：
	 * java.lang.IllegalArgumentException: Invalid character found in method name. HTTP method names must be tokens
	 * 
	 */
	public final static String ALLOWED_HEADS_VALUES = "Authorization,Postman-Token,Content-Type,Content-Length,Host,User-Agent,Accept,Accept-Encoding,Connection,Head-Signature";
	public final static String[] ALLOWED_HEADS = ALLOWED_HEADS_VALUES.split(",");

	public final static String EXPOSED_HEADS_VALUES = "Authorization,Head-Signature";
	public final static String[] EXPOSED_HEADS = EXPOSED_HEADS_VALUES.split(",");

	public final static String ALLOWED_MEDHODS_VALUES = "GET,HEAD,POST,PUT,DELETE,OPTIONS";
	public final static String[] ALLOWED_MEDHODS = ALLOWED_MEDHODS_VALUES.split(",");
}
