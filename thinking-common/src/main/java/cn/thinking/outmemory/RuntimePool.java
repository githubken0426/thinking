package cn.thinking.outmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行常量池内存溢出
 * 在运行时产生大量常量就可以实现让 Method Area 溢出的目的。
 * 运行是常量可以用 String 类的 intern 方法，不断地产生新的常量。
 *
 * @author ken
 * 2016-12-9 下午01:27:24
 */
public class RuntimePool {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
