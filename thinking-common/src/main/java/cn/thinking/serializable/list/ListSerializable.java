package cn.thinking.serializable.list;

import cn.thinking.CommonConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会进行用户自定义的序列化和反序列化。
 * 如果没有这样的方法，则默认调用是
 * ObjectOutputStream 的 defaultWriteObject 方法
 * ObjectInputStream 的 defaultReadObject 方法。
 * 用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。
 *
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:59
 * @Description:List的序列化
 */
public class ListSerializable {
    /**
     * why transient
     * ArrayList是动态数组，如果数组自动增长长度设为100，而实际只放了一个元素，
     * 为了保证在序列化的时候不会将空的元素进行序列化，ArrayList把元素数组设置为transient。
     * <p>
     * why writeObject and readObject
     * ArrayList使用transient来声明elementData。
     * 但是，作为一个集合，在序列化过程中还必须保证其中的元素可以被持久化下来，
     * 所以，通过重写writeObject 和 readObject方法的方式把其中的元素保留下来。
     * writeObject方法把elementData数组中的元素遍历的保存到输出流（ObjectOutputStream）中。
     * readObject方法从输入流（ObjectInputStream）中读出对象并保存赋值到elementData数组中
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        String file = CommonConfiguration.DESKTOP_PATH + File.separator + "List.txt";
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World!");
        System.out.println("List:" + list.toString());
        try {
            //写入
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(list);
            output.close();
            //读取
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            List<String> str = (List<String>) input.readObject();
            input.close();
            System.out.println("读取信息:");
            for (String s : str) {
                System.out.println(s + ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
