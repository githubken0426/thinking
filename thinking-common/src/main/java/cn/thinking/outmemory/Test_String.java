package cn.thinking.outmemory;

public class Test_String {
    /**
     * 存在于.class文件中的常量池，在运行期被JVM装载，并且可以扩充。
     * String的intern()方法就是扩充常量池的一个 方法；
     * 当一个String实例str调用intern()方法时，
     * Java查找常量池中是否有相同Unicode的字符串常量，
     * 如果有，则返回其的引用， 如果没有，则在常量池中增加一个Unicode等于str的字符串并返回它的引用
     *
     * @param args 2016-12-9 下午01:49:35
     */
    public static void main(String[] args) {
        String kill = "kill";
        String kill2 = new String("kill");
        String kill3 = "ki" + "ll";
        System.out.println(kill == kill3);
        System.out.println(kill == kill2.intern());
        //kill2.intern();
        System.out.println(kill == kill2);
    }
}
