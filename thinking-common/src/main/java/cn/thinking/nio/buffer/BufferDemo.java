package cn.thinking.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferDemo {

	static final String PATH = "C:\\ec_workspace\\thinking\\thinking-common\\src\\main\\java\\cn\\thinking\\nio\\PathTest.java";

	/**
	 * 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存
	 * ByteBuffer 
	 * MappedByteBuffer 
	 * CharBuffer 
	 * DoubleBuffer 
	 * FloatBuffer 
	 * IntBuffer
	 * LongBuffer 
	 * ShortBuffer
	 * 
	 * capacity 容量 Buffer 所能容纳数据元素的最大数量，也就是底层数组的容量值。在创建时被指定，不可更改。
	 * position 位置下一个被读或被写的位置 
	 * limit 上界 可供读写的最大位置，用于限制 position，position < limit 
	 * mark 标记 位置标记，用于记录某一次的读写位置，可以通过 reset重新回到这个位置
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
//		test();
//		testBuffer();
//		bufferAttributes();
//		testBufferMark();
		testBufferRewind();
	}

	static void test() {
		try (RandomAccessFile file = new RandomAccessFile(PATH, "rw")) {
			FileChannel channel = file.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(48);
			// read into buffer,向buffer写入输入，写模式.如果返回-1，表示到了文件末尾
			int bytesRead = channel.read(buffer);
			while (bytesRead != -1) {
				// make buffer ready for read。反转为读模式
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get()); // read 1 byte at a time
				}
				buffer.clear(); // make buffer ready for writing。反转为写模式
				bytesRead = channel.read(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void bufferAttributes() {
		try (FileChannel channel = FileChannel.open(Paths.get(PATH), StandardOpenOption.READ)) {
			ByteBuffer buffer = ByteBuffer.allocate(500);
			System.out.println("buffer.allocate(500) " + buffer.mark());
			int result = channel.read(buffer);
			System.out.println("channel.read(buffer) " + buffer.mark());
			// 循环File.size/capacity次。ByteBuffer.allocate(capacity)
			while (result != -1) {
				buffer.flip();
				System.out.println("\nbuffer.flip() " + buffer.mark());
				while (buffer.hasRemaining()) {
					CharBuffer charBuffer = Charset.forName("utf-8").decode(buffer);
					System.out.print(charBuffer.toString());
				}
				buffer.clear();
				System.out.println("\nbuffer.clear() " + buffer.mark());
				result = channel.read(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void testBufferMark() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.put("a".getBytes());
		buffer.put("b".getBytes());
		buffer.put("c".getBytes());
		//	经过标记后，会持续记住此位置
		buffer.position(1).mark();
		System.out.println("buffer.position(0).mark() " + buffer.mark()+"\n");
		while(buffer.hasRemaining()) {
			CharBuffer charBuffer= Charset.forName("utf-8").decode(buffer);
		    System.out.print(charBuffer.toString());
		    //  mark将会跳转到上次标记的位置
//		    buffer.reset();
		}
	}
	
	static void testBufferRewind() {
		ByteBuffer buffer = ByteBuffer.allocate(5);
		buffer.put("a".getBytes());
		buffer.put("b".getBytes());
		/**
		 * 	设置position=0，清除mark 
		 * 	和buffer.position(0)相比，除了清除mark外。
		 */
		buffer.rewind();
		System.out.println("buffer.rewind() " + buffer.mark()+"\n");
		buffer.put("c".getBytes());//c会覆盖a
		
		buffer.position(0);
		while(buffer.hasRemaining()) {
			CharBuffer charBuffer= Charset.forName("utf-8").decode(buffer);
		    System.out.print(charBuffer.toString());
		}
	}
	static void testBuffer() {
		CharBuffer buffer = CharBuffer.allocate(48);
		buffer.put("this is test");
		CharBuffer buffer2 = CharBuffer.allocate(48);
		buffer2.put("this is test");
		// flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值.
		buffer.flip();
		// equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。
		System.out.println("buffer.equals(buffer2):" + buffer.equals(buffer2));
		System.out.println("buffer.compareTo(buffer2):" + buffer.compareTo(buffer2));

		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println();

		buffer.limit(48);
		// position将被设回0，limit被设置成 capacity的值。Buffer被清空了（数据并未清除）。Buffer中未读的数据将被"遗忘"
		buffer.clear();
		/**
		 * 如果缓冲区中仍有未读数据，并且想稍后读取它，但需要先写入一些数据，这时候应该调用 compact()
		 * 它会将所有未读数据复制到 Buffer 的开头，然后它将 position设置在最后一个未读元素之后。 limit属性仍设置为 capacity。
		 */
		buffer.compact();
		buffer.mark();
		buffer.reset();
		System.out.println(buffer.mark());
	}
}
