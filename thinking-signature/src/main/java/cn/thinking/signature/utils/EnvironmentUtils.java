package cn.thinking.signature.utils;

import org.apache.commons.lang3.StringUtils;

public class EnvironmentUtils {
    public static String getEnvironment(String name) {
        return System.getenv(name) == null ? StringUtils.EMPTY : System.getenv(name);
    }
}
