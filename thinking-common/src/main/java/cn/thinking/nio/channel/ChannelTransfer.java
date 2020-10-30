package cn.thinking.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * FileChannel 从文件中读写数据。 
 * DatagramChannel 能通过UDP读写网络中的数据。 
 * SocketChannel能通过TCP读写网络中的数据。
 * ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 */
public class ChannelTransfer {

	static void from() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();
		/**
		  *    表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数.
		 * SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）.
		 */
		toChannel.transferFrom(fromChannel, position, count);

		fromChannel.transferTo(position, count, toChannel);
	}
}
