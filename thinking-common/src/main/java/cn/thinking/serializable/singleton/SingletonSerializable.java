package cn.thinking.serializable.singleton;

import java.io.Serializable;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:47
 * @Description:
 */
public class SingletonSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private volatile static SingletonSerializable singleton = null;

    private SingletonSerializable() {
    }

    public static SingletonSerializable getInstance() {
        if (singleton == null) {
            synchronized (SingletonSerializable.class) {
                if (singleton == null)
                    singleton = new SingletonSerializable();
            }
        }
        return singleton;
    }
}
