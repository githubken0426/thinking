package cn.thinking.classess;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * instanceof isInstance() isAssignableFrom()方法对比
 * 1:instanceof 针对实例 适合直接类型的检查，常与普通的Class对象出现。
 * <p>
 * 2:Class1.isAssignableFrom(Class2<?> cls)： 判断Class1和Class2是否相同
 * 即判断Class1是否是Class2的超类或接口，调用者和参数都是java.lang.Class类型。
 * <p>
 * 3:Class.isInstance(Object obj) obj是被测试的对象，如果obj是调用这个方法的class或接口的实例，则返回true
 * 适合泛类型的检测（如代理，接口，抽象类等规则），常与泛化Class对象出现
 *
 * @author ken 2016-3-25 下午04:13:48
 */
public class InstanceBelong {

    public static void main(String[] args) {
        String str = "str";
        System.out.println("instanceof:" + (str instanceof String));
        /**
         * 通过模拟源码看到，如果传入参数是xxx.class 源码会调用xxx.class.getClass()方法 最终返回class
         * java.lang.Class 而不是你要返回的那个xxx对象 所以传入参数最好是实例，而不是class对象
         */
        Class<? extends Object> s = String.class.getClass();
        System.out.println(s);// 输出 class java.lang.Class
        System.out.println("isInstance()1:" + str.getClass().isInstance(String.class)); // 输出false
        System.out.println("isInstance()2:" + String.class.isInstance(str.getClass())); // 同上，输出false
        System.out.println("isInstance()3:" + String.class.isInstance(str)); // 输出true
        System.out.println("自定义isInstance():" + InstanceBelong.isInstance(str, String.class));// 输出true
        /**
         * 此处name返回的为String类型(与getSimpleName()即其类名无关), 也就是name定义为String类型 与bn2效果相同
         */
        String name = str.getClass().getSimpleName();
        System.out.println("\nbn:" + String.class.isInstance(name));
        System.out.println("bn2:" + String.class.isInstance(str));
        // Object是所有类的超类
        System.out.println("\nisAssginableFrom():" + Object.class.isAssignableFrom(ArrayList.class));// 输出true
        System.out.println("isAssginableFrom():" + ArrayList.class.isAssignableFrom(Object.class)); // 输出false
    }

    /**
     * 模拟isInstance()源码实现
     *
     * @param obj 模拟传入的参数
     * @param cls 模拟class对象
     * @return 2016-12-13 上午10:09:35
     */
    public static boolean isInstance(Object obj, Class<?> cls) {
        if (obj == null) {
            return false;
        }
        Class<? extends Object> cl = obj.getClass();
        if (cl == cls) {// 检查当前类的实现
            return true;
        }
        if (Arrays.asList(obj.getClass().getInterfaces()).contains(cls)) {// 检查接口的实现
            return true;
        }
        /**
         * 如果查询得到的话返回 true; 否则在java.lang.Object.class处终止,这里需要注意，
         * a.getClass().getInterfaces()是获取到了所有的接口，所以接下来不给接口做检查
         */
        Class<? extends Object> superClass = obj.getClass().getSuperclass();
        while (superClass != Object.class) {
            if (superClass == cls) {
                return true;
            }
            superClass = superClass.getClass();
        }
        return false;
    }
}
