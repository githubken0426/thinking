package cn.thinking.serializable.singleton;

import cn.thinking.CommonConfiguration;

import java.io.*;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:51
 * @Description:
 */
public class SingletonMain {
    /**
     * 通过对Singleton的序列化与反序列化得到的对象是一个新的对象，这就破坏了Singleton的单例性
     * 序列化会通过反射调用无参数的构造方法创建一个新的对象
     private Object readOrdinaryObject(boolean unshared)throws IOException{
         //此处省略部分代码
         Object obj;
         try {
            obj = desc.isInstantiable() ? desc.newInstance() : null;
         } catch (Exception ex) {
            throw (IOException) new InvalidClassException
                (desc.forClass().getName(),"unable to create instance").initCause(ex);
         }
         //此处省略部分代码
         if (obj != null && handles.lookupException(passHandle) == null && desc.hasReadResolveMethod()){
            Object rep = desc.invokeReadResolve(obj);
             if (unshared && rep.getClass().isArray()) {
                rep = cloneArray(rep);
             }
             if (rep != obj) {
                handles.setObject(passHandle, obj = rep);
             }
        }
        return obj;
     }
     */
    /**
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String file = CommonConfiguration.DESKTOP_PATH + File.separator + "Singleton.txt";
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
        os.writeObject(SingletonSerializable.getInstance());
        os.close();

        ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        SingletonSerializable singleton = (SingletonSerializable) is.readObject();
        is.close();
        //false
        System.out.println(singleton == SingletonSerializable.getInstance());
    }
}
