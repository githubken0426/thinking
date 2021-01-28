package cn.thinking.nio.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。 FileChannel无法设置为非阻塞模式，他总是运行在阻塞模式下。
 * 
 * @author kun.f.wang
 *
 */
public class FileChannelTest {
	static final String SOURCE_PATH = "C:\\ec_workspace\\thinking\\thinking-common\\src\\main\\java\\cn\\thinking\\nio\\channel\\FileChannelTest.java";
	static final String TARGET_PATH = "C:\\Users\\kun.f.wang\\Desktop\\write_fo.txt";

	/**
	 * "r" 打开文件仅仅是为了读取数据，如果尝试调用任何写入数据的操作都会造成返回IOException错误信息的问题。
	 * "rw"打开文件用于读写两种操作，如果文件本身并不存在，则会创建一个全新的文件。
	 * "rwd"打开文件用于读写两种操作，每当进行写操作，同步的刷新到磁盘，刷新内容和元数据。 
	 * "rws"每当进行写操作，同步的刷新到磁盘，刷新内容。
	 * 
	 * @throws IOException
	 */
	static void fileChannelRead() throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(SOURCE_PATH, "r")) {
			FileChannel channel = file.getChannel();
			System.out.println("channel.size()返回文件的大小:" + channel.size());
			/**
			 * 截取文件，文件将中指定长度后面的部分将被删除
			 */
//			channel.truncate(4096);
			/**
			 * FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。
			 * 出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法。
			 * force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上
			 */
			channel.force(true);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int bytesRead = channel.read(buffer);
			while (bytesRead != -1) {
				// make buffer ready for read
				buffer.flip();
				while (buffer.hasRemaining()) {
					//	设置编码
					CharBuffer charBuffer = Charset.forName("UTF-8").decode(buffer);
					System.out.println(charBuffer.toString());
				}
				buffer.clear(); // make buffer ready for writing
				bytesRead = channel.read(buffer);
			}
		}
	}
	/**
	 * 通过读取source buffer写入到target channel
	 * 建议使用： transferFrom/transferTo
	 */
	static void fileChannelWrite() {
		System.out.println("start read ");
		try {
			//获取path的两种方式
			Path sourcePath = new File(SOURCE_PATH).toPath();
			FileChannel sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);
			Path targetPath = Paths.get(TARGET_PATH);
			FileChannel targetChannel = FileChannel.open(targetPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			
			ByteBuffer sourceBuffer = ByteBuffer.allocate(48);
			sourceBuffer.put("start write!\n".getBytes());
			
			int sourceResult = sourceChannel.read(sourceBuffer);
			while (sourceResult != -1) {
				sourceBuffer.flip();
				while (sourceBuffer.hasRemaining()) {
					targetChannel.write(sourceBuffer);
				}
				sourceBuffer.clear(); 
				sourceResult = sourceChannel.read(sourceBuffer);
			}
			System.out.println("read end, sourceResult:" + sourceResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void fileChannelWrite(String outPath) {
		try (RandomAccessFile file = new RandomAccessFile(outPath, "rw")) {
			FileChannel channel = file.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(48);
			buffer.clear();
			String newData = "New String to write to file..." + System.currentTimeMillis();
			buffer.put(newData.getBytes());
			buffer.flip();
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void fileChannelWrite(String source, String target) throws IOException {
		try (RandomAccessFile sourceFile = new RandomAccessFile(source, "rw");
				RandomAccessFile targetFile = new RandomAccessFile(target, "rw");) {
			FileChannel sourceChannel = sourceFile.getChannel();
			FileChannel targetChannel = targetFile.getChannel();

			long position = 0;
			long count = sourceChannel.size();
			/**
			 * 表示从position处开始向目标文件写入数据，count表示最多传输的字节数。
			 * 如果源通道的剩余空间小于 count个字节，则所传输的字节数要小于请求的字节数.
			 * SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）.
			 */
//			targetChannel.transferFrom(sourceChannel, position, count);
			sourceChannel.transferTo(position, count, targetChannel);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) throws IOException {
//		fileChannelRead();
		fileChannelWrite();
//		fileChannelWrite(SOURCE_PATH, TARGET_PATH);
		
	}
}
