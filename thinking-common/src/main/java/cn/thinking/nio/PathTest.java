package cn.thinking.nio;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

	/**
	 * BIO NIO 面向流 面向缓冲 阻塞IO 非阻塞IO 无 选择器
	 * 
	 * 面向流与面向缓冲
	 * BIO是面向流的，意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，它不能前后移动流中的数据。如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。
	 * NIO面向缓冲,NIO的数据读取到缓冲区，需要时可在缓冲区中前后移动。处理数据比较灵活,但是需要检查该缓冲区中是否含有需要处理的数据。而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据。
	 * 
	 * 阻塞与非阻塞 BIO的各种流是阻塞的。这意味着，当一个线程调用read() 或
	 * write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情。
	 * NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。
	 * 而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。
	 * 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。
	 * 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。
	 * 
	 * 选择器（Selectors）
	 * NIO的选择器允许一个单独的线程来监视多个输入通道，你可以注册多个通道使用一个选择器，然后使用一个单独的线程来“选择”通道：这些通道里已经有可以处理的输入，或者选择已准备写入的通道。
	 * 这种选择机制，使得一个单独的线程很容易来管理多个通道。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Path absolate = Paths.get("C:\\Windows\\setuperr.log");
		System.out.println(absolate);
		Path relative = Paths.get("C:\\Windows", "setuperr.log");
		System.out.println(relative);
		Path currentDir = Paths.get(".");
		System.out.println(currentDir.toAbsolutePath());
		Path parentDir = Paths.get("..");
		System.out.println(parentDir.toAbsolutePath());
		System.out.println(parentDir.normalize());

		boolean pathExists = Files.exists(absolate, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
		System.out.println(pathExists);
	}
}
