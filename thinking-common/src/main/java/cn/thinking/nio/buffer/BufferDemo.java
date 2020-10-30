package cn.thinking.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
	
	static final String PATH = "C:\\ec_workspace\\thinking\\thinking-common\\src\\main\\java\\cn\\thinking\\nio\\buffer\\BufferDemo.java";

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
	 * capacity 容量	Buffer 所能容纳数据元素的最大数量，也就是底层数组的容量值。在创建时被指定，不可更改。
	 * position 位置	下一个被读或被写的位置
	 * limit 上界	可供读写的最大位置，用于限制 position，position < limit
	 * mark 标记	位置标记，用于记录某一次的读写位置，可以通过 reset 重新回到这个位置
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			test();
			testBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void test() throws IOException {
		RandomAccessFile file = new RandomAccessFile(PATH, "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int bytesRead = channel.read(buffer); // read into buffer.
		while (bytesRead != -1) {
			// make buffer ready for read
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get()); // read 1 byte at a time
			}
			buffer.clear(); // make buffer ready for writing
			bytesRead = channel.read(buffer);
		}
		file.close();
	}

	static void testBuffer() throws IOException {
		CharBuffer buffer = CharBuffer.allocate(48);
		buffer.put("this is test");
		CharBuffer buffer2 = CharBuffer.allocate(48);
		buffer2.put("this is test");
		//flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值.
		buffer.flip();
		// equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。
		System.out.println("buffer.equals(buffer2):" + buffer.equals(buffer2));
		System.out.println("buffer.compareTo(buffer2):" + buffer.compareTo(buffer2));

		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
		System.out.println();
		
		buffer.limit(48);
		//position将被设回0，limit被设置成 capacity的值。Buffer被清空了（数据并未清除）。Buffer中未读的数据将被"遗忘"
		buffer.clear();
		//compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit设置成capacity。
		//Buffer准备好写数据了，但是不会覆盖未读的数据。
		buffer.compact();
		buffer.mark();
		buffer.reset();
		System.out.println(buffer.position());
	}
}
