package cn.thinking.jni;

/**
 * java native interface dynamic link library java调用动态库
 *
 * @author Administrator 2016-1-13 上午10:01:45
 */
public class MyJNI {
    static {
        // System.out.println(System.getProperty("java.library.path"));
        // 库的扩展名字可以不用写出来，究竟是DLL还是SO，由系统自己判定。前提：将dll拷贝到此类包下
        System.loadLibrary("user32");
    }

    // 还需对将要调用的方法做本地声明,不需要具体实现
    public native static void MessageBoxA();

    public native static int getAge();

    /**
     * java.lang.UnsatisfiedLinkError: cn.dll.MyJNI.MessageBoxA()V
     * user32已经固定了包名，所以需要在应用中调用时，需要创建这个包
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(MyJNI.getAge());
            MyJNI.MessageBoxA();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}