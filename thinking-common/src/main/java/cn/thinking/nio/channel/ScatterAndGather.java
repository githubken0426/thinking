package cn.thinking.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
   * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中,
   * 因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中.
 * 
   * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，
   * 因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel
 * @author kun.f.wang
 */
public class ScatterAndGather {

	static void scatterRead() throws IOException {
		try (RandomAccessFile file = new RandomAccessFile("", "rw")) {
			FileChannel channel = file.getChannel();
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			/**
			 * read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer。
			   *  必须第一个buffer被写满后，channel才会向另一个buffer中写。 这也意味着它不适用于动态消息(消息大小不固定)。
			 */
			ByteBuffer[] bufferArray = { header, body };
			channel.read(bufferArray);
		}
	}

	static void gatherWrite() throws IOException {
		try (RandomAccessFile file = new RandomAccessFile("", "rw")) {
			FileChannel channel = file.getChannel();
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			/**
			 * 将数据写入到channel，注意只有position和limit之间的数据才会被写入。
			 * 因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，那么这58byte的数据将被写入到channel中。
			 * 因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息。
			 */
			ByteBuffer[] bufferArray = { header, body };
			channel.write(bufferArray);
		}
	}
}
