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

public class SocketChannelTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serverSocketWithSelector();
//					serverSocketChannel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		TimeUnit.SECONDS.sleep(5);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					clientSocketChannel();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	static final int port = 8081;

	/**
	 * 虽然我们可以自己处理每一个Socket事件，比如读写数据，不过更常规的方式是注册一个选择器。这个选择器侦听着数据的变化事件。
	 * 每个注册的通道都有自己的SelectionKey,用这个可以区分到底是哪个通道产生了事件
	 * 
	 * @throws IOException
	 */
	public static void serverSocketWithSelector() throws IOException {
		try (ServerSocketChannel channel = ServerSocketChannel.open()) {
			channel.configureBlocking(false);
			channel.bind(new InetSocketAddress(port));

			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("serverSocketWithSelector 准备就绪！");
			for (;;) {
				int selectorCount = selector.select();
				if (selectorCount < 1)
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

	/**
	 * ServerSocketChannel 1、监听新进来的TCP链接通道， 2、创建新的SocketChannel
	 * ServerSocketChannel并不能进行数据传输的能力
	 * 
	 * @throws IOException
	 */
	public static void serverSocketChannel() throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		// 1设置为非阻塞模式
		channel.configureBlocking(false);
		channel.bind(new InetSocketAddress(port));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		System.out.println("服务器准备接收数据");
		for (;;) {
			/**
			 * 在服务器端，接收客户端的链接，如果存在客户端的话，就返回一个SocketChannel对象.
			 * 如果是阻塞模式的话，没有新的链接进来，就会阻塞在这里，否则，往下执行 ;
			 * 如果是非阻塞模式的话，没有新的链接进来，就会立马返回一个null，程序不会阻塞在这里， 会立马往下进行的;
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
	}

	public static void clientSocketChannel() throws IOException {
		try (SocketChannel channel = SocketChannel.open()) {
			channel.connect(new InetSocketAddress("localhost", port));
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put("clientSocketChannel".getBytes());
			buffer.flip();
			while (buffer.hasRemaining()) {
				channel.write(buffer);
			}
		}
	}
}
