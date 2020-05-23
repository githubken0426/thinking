package cn.thinking.singleton;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 13:35
 * @Description:
 */
public class Singleton {
    /*
     * 1. 线程不安全. 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作。
     */
    private static Singleton instance;

    private static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /*
     * 2. 线程安全. 这种写法能够在多线程中很好的工作， 它也具备很好的lazy loading，
     * 但是，效率很低 大多数情况下不需要同步
     */
    private static Singleton instance2;

    private static synchronized Singleton getInstance2() {
        if (instance2 == null) {
            instance2 = new Singleton();
        }
        return instance2;
    }

    /*
     * 3. 这种方式基于classloder机制避免了多线程的同步问题， 不过，instance在类装载时就实例化，
     * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance方法，
     * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，
     * 这时候初始化instance显然没有达到lazy loading的效果
     */
    private static Singleton instance3;

    static {
        instance3 = new Singleton();
    }

    public static Singleton getInstance3() {
        return instance3;
    }

    /*
     * 4 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static final Singleton getInstance4() {
        return SingletonHolder.INSTANCE;
    }

    /*
     * 5.枚举单例 这种方式是Effective Java作者Josh Bloch 提倡的方式，
     * 它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象; 详见SingletonEnum 中写法
     */

    /*
     * 6. 双重校验锁
     * 一个定义为volatile的变量是说这变量可能会被意想不到地改变， 这样，编译器就不会去假设这个变量的值了。
     * 精确地说就是，优化器在用到这个变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份。
     * 问题：
     * 1.如果单例由不同的类装载器装入，那便有可能存在多个单例类的实例。假定不是远端存取，
     * 例如一些servlet容器对每个servlet使用完全不同的类装载器，
     * 这样的话如果有两个servlet访问一个单例类，它们就都会有各自的实例。
     * 2.如果Singleton实现了java.io.Serializable接口，那么这个类的实例就可能被序列化和复原。
     * 不管怎样，如果你序列化一个单例类的对象，接下来复原多个那个对象，那你就会有多个单例类的实例。
     */
    private volatile static Singleton INSTANCE6;

    public static Singleton getInstance6() {
        if (INSTANCE6 == null) {
            synchronized (Singleton.class) {
                if (INSTANCE6 == null) {
                    INSTANCE6 = new Singleton();
                }
            }
        }
        return INSTANCE6;
    }

    /**
     * 测试枚举方法
     *
     * @param args
     */

    public static void main(String[] args) {
        // 遍历枚举
        for (SingletonEnum c : SingletonEnum.values()) {
            System.out.println(c.sayHi());
        }
        SingletonEnum.INSTANCE.getInstance();
    }
}
