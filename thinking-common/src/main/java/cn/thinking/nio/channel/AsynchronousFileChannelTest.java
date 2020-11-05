package cn.thinking.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * AsynchronousFileChannel可以实现异步地读取和写入文件数据
 * 
 * @author kun.f.wang
 *
 */
public class AsynchronousFileChannelTest {
	static String file_path = "C:\\Users\\kun.f.wang\\Desktop\\test.txt";

	public static void main(String[] args) throws IOException {
		asychronousWrite();
//		asychronousRead();
	}

	static void asychronousRead() throws IOException {
		Path path = Paths.get(file_path);
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate(48);
		long position = 0;
		Future<Integer> future = channel.read(buffer, position);
		while (!future.isDone())
			;
		buffer.flip();
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		System.out.println(new String(data));
		buffer.clear();
		System.out.println("********************Read done********************");

		channel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
			/**
			 * result读取的字节数。 attachment 用来存储读取的数据(当前示例中，我们选用 ByteBuffer
			 * 来存储数据，其实我们也可以选用其他的类型)
			 */
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				System.out.println("data bytes result: " + result);
				attachment.flip();
				byte[] data = new byte[attachment.limit()];
				attachment.get(data);
				System.out.println(new String(data));
				attachment.clear();
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("Read failed");
				exc.printStackTrace();
			}
		});
		System.out.println("********************CompletionHandler Read done********************");
	}

	static void asychronousWrite() throws IOException {
		System.out.println("********************Write start********************");
		Path path = Paths.get(file_path);
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
		AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(48);
		buffer.put("test data".getBytes());
		buffer.flip();
		Future<Integer> operation = channel.write(buffer, 0);
		buffer.clear();
		while (!operation.isDone())
			;
		System.out.println("********************Write done********************");

		ByteBuffer completionBuffer = ByteBuffer.allocate(48);
		completionBuffer.put("CompletionHandler write".getBytes());
		completionBuffer.flip();
		channel.write(completionBuffer, buffer.position(), completionBuffer, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				System.out.println("bytes written: " + result);
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("Write failed");
				exc.printStackTrace();
			}
		});
		completionBuffer.clear();
		System.out.println("********************CompletionHandler Write done********************");
	}
}
