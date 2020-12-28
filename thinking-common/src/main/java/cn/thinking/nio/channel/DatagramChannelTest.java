package cn.thinking.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包。
 * send，receive 前无需建立连接，read，write前必须建立连接，线程安全，任意时刻只能有一个线程进行读取和写入。
 * 
 * UDP（User Data Protocol，用户数据报协议） 1、UDP是一个非连接的协议，传输数据之前源端和终端不建立连接，
 * 当它想传送时就简单地去抓取来自应用程序的数据，并尽可能快地把它扔到网络上。 在发送端，UDP传送数据的速度仅仅是受应用程序生成数据的速度、
 * 计算机的能力和传输带宽的限制； 在接收端，UDP把每个消息段放在队列中，应用程序每次从队列中读一个消息段。
 * 
 * 2、 由于传输数据不建立连接，因此也就不需要维护连接状态，包括收发状态等， 因此一台服务机可同时向多个客户机传输相同的消息。
 * 
 * 3、UDP信息包的标题很短，只有8个字节，相对于TCP的20个字节信息包的额外开销很小。
 * 
 * 4、吞吐量不受拥挤控制算法的调节，只受应用软件生成数据的速率、传输带宽、 源端和终端主机性能的限制。
 * 
 * 5、UDP使用尽最大努力交付，即不保证可靠交付， 因此主机不需要维持复杂的链接状态表（这里面有许多参数）。
 * 
 * 6、UDP是面向报文的。发送方的UDP对应用程序交下来的报文， 在添加首部后就向下交付给IP层。既不拆分，也不合并，而是保留这些报文的边界，
 * 因此，应用程序需要选择合适的报文大小。
 * 
 * @author kun.f.wang
 */
public class DatagramChannelTest {
	private static int PORT = 8089;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				datagramSend();
			}
		}).start();
		TimeUnit.SECONDS.sleep(5);
		new Thread(new Runnable() {
			@Override
			public void run() {
				datagramReceive();
			}
		}).start();
	}

	public static void datagramReceive() {
		try (DatagramChannel channel = DatagramChannel.open()) {
			channel.bind(new InetSocketAddress(PORT));
			ByteBuffer buffer = ByteBuffer.allocate(48);
			buffer.clear();
			// receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃
			channel.receive(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void datagramReceive(Selector selector) {
		try (DatagramChannel datagramChannel = DatagramChannel.open()) {
			datagramChannel.configureBlocking(false);
			datagramChannel.bind(new InetSocketAddress(PORT));
			datagramChannel.register(selector, SelectionKey.OP_READ);

			while (selector.select() > 0) {
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					if (selectionKey.isReadable()) {
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						datagramChannel.receive(buffer);
						buffer.flip();

						System.out.println("receive:" + new String(buffer.array(), 0, buffer.limit()));
						buffer.clear();
					}
				}
				iterator.remove();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void datagramSend() {
		try (DatagramChannel channel = DatagramChannel.open()) {
			channel.configureBlocking(false);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNext()) {
				String str = scanner.next();
				buffer.put((new Date().toString() + " >> " + str).getBytes());
				buffer.flip();

				channel.send(buffer, new InetSocketAddress("127.0.0.1", PORT));
				buffer.clear();
			}
			channel.close();
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
