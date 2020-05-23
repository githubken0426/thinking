package entity.serializable;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 14:26
 * @Description:
 */
public class Stream implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient InputStream is;
    private int majorVer;
    private int minorVer;

    public Stream(InputStream is) throws IOException {
        System.out.println("Stream(InputStream) called!");
        this.is = is;
        DataInputStream dis;
        if (is instanceof DataInputStream)
            dis = (DataInputStream) is;
        else
            dis = new DataInputStream(is);
        if (dis.readInt() != 0xcafebabe)
            throw new IOException("Not a class file!");
        minorVer = dis.readShort();
        majorVer = dis.readShort();
    }

    public int getMajorVer() {
        return majorVer;
    }

    public int getMinorVer() {
        return minorVer;
    }

    public void showIS() {
        System.out.println(is);
    }
}
