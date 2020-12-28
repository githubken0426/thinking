package cn.thinking.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ServerSocketChannel： 监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 * read，write 前必须建立连接，线程安全，任意时刻只能有一个线程进行读取和写入。
 * 
 * TCP（Transmission Control Protocol，传输控制协议）是面向连接的协议，也就是说，在收发数据前，必须和对方建立可靠的连接。
 * 一个TCP连接必须要经过三次“对话”才能建立起来，而断开连接要进行4次。
 * @author kun.f.wang
 */
public class ServerSocketChannelTest {
	static final int port = 8081;
	public static void main(String[] args) throws IOException, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serverSocketChannel(Selector.open());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(5);
		new Thread(new Runnable() {
			@Override
			public void run() {
				clientSocketChannel();
			}
		}).start();
	}

	/**
	 * 虽然我们可以自己处理每一个Socket事件，比如读写数据，不过更常规的方式是注册一个选择器。
	 * 这个选择器侦听着数据的变化事件。每个注册的通道都有自己的SelectionKey,用这个可以区分到底是哪个通道产生了事件。
	 * 
	 * ServerSocketChannel并不能进行数据传输的能力。
	 * 1、监听新进来的TCP链接通道
	 * 2、创建新的SocketChannel
	 * @throws IOException
	 */
	public static void serverSocketChannel(Selector selector)  {
		try (ServerSocketChannel channel = ServerSocketChannel.open()) {
			channel.configureBlocking(false);
			channel.bind(new InetSocketAddress(port));
//			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("serverSocketWithSelector 准备就绪！");
			for (;;) {
				int selectorCount = selector.select();
				if (selectorCount < 1)
					//表示没有数据发送过来。非阻塞模式下,可以趁着还没数据发送过来的时候做一些其他操作，以此提高效率
					continue;
				Set<SelectionKey> selectionKeys = selector.selectedKeys();

				Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
				while (keyIterator.hasNext()) {
					SelectionKey selection = keyIterator.next();
					if (selection.isAcceptable()) {
						System.out.println("Acceptable:" + selection.isAcceptable());
						handleAccept(selection);
					} else if (selection.isConnectable()) {
						System.out.println("Connectable:" + selection.isConnectable());
					} else if (selection.isReadable()) {
						System.out.println("Readable:" + selection.isReadable());
						handleRead(selection);
					} else if (selection.isWritable()) {
						System.out.println("Writable:" + selection.isWritable());
					}
					keyIterator.remove();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleAccept(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
		socketChannel.configureBlocking(false);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, buffer);
		System.out.println("Got connection from " + socketChannel);
	}

	private static void handleRead(SelectionKey selectionKey) throws IOException {
		// 1 获取套接字通道
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		// 2 获取缓冲器并进行重置 ,selectionKey.attachment() 为获取选择器键的附加对象
		ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
		buffer.clear();
		// 3 没有内容则关闭通道
		if (socketChannel.read(buffer) == -1) {
			socketChannel.close();
			return;
		}
		// 4 将缓冲器转换为读状态
		buffer.flip();
		// 5将缓冲器中接收到的值按 localCharset 格式编码保存
		String receivedData = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
		System.out.println("接收到客户端的请求数据： " + receivedData);
		// 6返回响应数据给客户端
		String responseData = "已接收到你的请求数据，响应数据为： ( 响应数据 )";
		ByteBuffer responseBuffer = ByteBuffer.wrap(responseData.getBytes("UTF-8"));
		socketChannel.write(responseBuffer);
		// 7关闭通道
		socketChannel.close();
	}

	public static void serverSocketChannel() throws IOException {
		try (ServerSocketChannel channel = ServerSocketChannel.open()) {
			// 1设置为非阻塞模式
			channel.configureBlocking(false);
			channel.bind(new InetSocketAddress(port));
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			System.out.println("服务器准备接收数据");
			for (;;) {
				/**
				 * ServerSocketChannel的阻塞与非阻塞主要体现在accept方法上。
				 * 
				 * 阻塞模式： 没有接收到客户端请求，就会一直阻塞，直到accept方法获取到SocketChannel对象为止;
				 * 非阻塞模式： 调用accept方法如果返回null，则表示没有收到请求，此时可以做其他的事，之后再继续调用accept看看有没有请求到来，如此循环；
				 * 			如果accept返回了SocketChannel实例，那么此时就与客户端请求建立了连接，通过该SocketChannel实例可以发送与接收数据了。
				 */
				SocketChannel socketChannel = channel.accept();
				if (null != socketChannel) {
					int bytesRead = socketChannel.read(buffer);
					while (bytesRead != -1) {
						// make buffer ready for read
						buffer.flip();
						while (buffer.hasRemaining()) {
							System.out.print((char) buffer.get()); // read 1 byte at a time
						}
						buffer.clear(); // make buffer ready for writing
						bytesRead = socketChannel.read(buffer);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clientSocketChannel() {
		try (SocketChannel channel = SocketChannel.open()) {
			channel.connect(new InetSocketAddress("localhost", port));
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put("clientSocketChannel".getBytes());
			buffer.flip();
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
