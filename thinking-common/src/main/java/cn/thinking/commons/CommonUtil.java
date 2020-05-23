package cn.thinking.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 数组输出纯字符串
     *
     * @param t
     * @return
     */
    public static <T> String arrayToString(T[] t) {
        if (t == null)
            return "";
        List<T> list = Arrays.asList(t);
        Iterator<T> it = list.iterator();
        if (!it.hasNext())
            return "";
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            T e = it.next();
            sb.append(e);
            if (!it.hasNext())
                return sb.toString();
            sb.append(",");
        }
    }

    /**
     * 提取出手机号
     *
     * @return
     * @throws
     * @date 2018年4月28日 下午1:46:21
     */
    public static String matcherPhone(String telephone) {
        if (StringUtils.isBlank(telephone))
            return "";
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(regExp);
        List<String> result = Arrays.asList(telephone.split(","));
        for (String str : result) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches())
                return str;
        }
        return "";
    }
}