package org.thinking.volume21.concurrence;

import java.text.SimpleDateFormat;

public class DateTimeUtil {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static String getDateTime(long timestamp) {
		return format.format(timestamp);
	}

}
