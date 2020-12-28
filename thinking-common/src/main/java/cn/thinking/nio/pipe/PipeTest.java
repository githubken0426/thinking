package cn.thinking.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.Date;
import java.util.Scanner;

public class PipeTest {
	public static void main(String[] args) throws IOException {
		pipe();
	}
	/**
	 * Pipe管道是2个线程之间的单向数据连接。
	 * Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。
	 * @throws IOException
	 */
	public static void pipe() throws IOException {
		System.out.println("start");
		Pipe pipe = Pipe.open();
		Pipe.SinkChannel channel = pipe.sink();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			buffer.clear();
			String str = scanner.next();
			buffer.put((new Date().toString() + " >> " + str).getBytes());
			buffer.flip();
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
			
			Pipe.SourceChannel sourceChannel = pipe.source();
			buffer.flip();
			//read()方法来读取数据，返回的int值会告诉我们多少字节被读进了缓冲区。
	        int len = sourceChannel.read(buffer);
	        System.out.println("receive: "+new String(buffer.array(), 0, len));
		}
		System.out.println("end");
		scanner.close();
	}
}
