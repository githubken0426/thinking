package cn.thinking.coseable;

import java.io.Closeable;
import java.io.IOException;

/**
 * 在java中jvm虚拟机会自动去调用gc(垃圾回收器)去回收堆中没有被引用的对象，至于什么时候回收，是不确定的，
 * 同时有些是用到其他资源，jvm也不会进行回收，类似Io流中的FileInputStream使用到了硬盘资源，垃圾回收器是不会去回收的，因此，必须手动关闭掉.
 * 在java.io.包下 InputStream, OutputStream, Reader, Writer 等基类都实现了Closeable接口，因为每次的IO操作结束之后都要去释放资源.
 * 
 * @author kun.f.wang
 */
public class CloseableImpl implements Closeable {

	@Override
	public void close() throws IOException {
		System.out.println(CloseableImpl.class.getSimpleName() + ".close()");
	}

}
