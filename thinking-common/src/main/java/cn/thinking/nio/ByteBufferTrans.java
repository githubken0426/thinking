package cn.thinking.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 	ByteBuffer是将数据移进移出通道的唯一方式，并且我们只能创建一个独立的基本类型缓冲器,
 * 	它提供了读写其他数据类型的方法,且信道的读写方法只接收ByteBuffer.
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 18:22
 * @Description:
 */
public class ByteBufferTrans {
    //	将字符转为字节(编码)
    public byte[] getBytes(char[] chars) {
        Charset charSet = Charset.forName("UTF-8");
        CharBuffer charBuffer = CharBuffer.allocate(chars.length);
        charBuffer.put(chars);
        charBuffer.flip();
        ByteBuffer byteBuffer = charSet.encode(charBuffer);
        return byteBuffer.array();
    }

    public char[] getChars(byte[] bytes) {//	将字节转为字符(解码)
        Charset charSet = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        CharBuffer cb = charSet.decode(byteBuffer);
        return cb.array();
    }

    public static Long bytesToLong(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes, 0, bytes.length);
        byteBuffer.flip();//need flip
        return byteBuffer.getLong();
    }

}
