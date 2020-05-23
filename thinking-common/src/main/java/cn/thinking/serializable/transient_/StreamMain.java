package cn.thinking.serializable.transient_;

import entity.serializable.Stream;

import java.io.*;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:29
 * @Description:
 */
public class StreamMain {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Stream stream = new Stream(new FileInputStream(args[0]));
        System.out.printf("Minor version number: %d%n", stream.getMinorVer());
        System.out.printf("Major version number: %d%n", stream.getMajorVer());
        stream.showIS();

        try (FileOutputStream fos = new FileOutputStream("x.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(stream);
        }
        try (FileInputStream fis = new FileInputStream("x.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            System.out.println();
            Stream readStrea = (Stream) ois.readObject();
            System.out.printf("Minor version number: %d%n", readStrea.getMinorVer());
            System.out.printf("Major version number: %d%n", readStrea.getMajorVer());
            readStrea.showIS();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
