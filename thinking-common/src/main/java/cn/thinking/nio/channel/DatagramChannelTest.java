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
 * DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包
 * 
 * @author kun.f.wang
 */
public class DatagramChannelTest {
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					datagramSend(8081);
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
					datagramReceive(8081);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void datagramReceive() throws IOException {
		DatagramChannel channel = DatagramChannel.open();
		channel.bind(new InetSocketAddress(8081));
		ByteBuffer buffer = ByteBuffer.allocate(48);
		buffer.clear();
		// receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃
		channel.receive(buffer);

	}

	public static void datagramReceive(int port) throws IOException {
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.configureBlocking(false);
		datagramChannel.bind(new InetSocketAddress(port));
		Selector selector = Selector.open();
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
	}

	public static void datagramSend(int port) throws IOException {
		DatagramChannel channel = DatagramChannel.open();
		channel.configureBlocking(false);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String str = scanner.next();
			buffer.put((new Date().toString() + " >> " + str).getBytes());
			buffer.flip();

			channel.send(buffer, new InetSocketAddress("127.0.0.1", port));
			buffer.clear();
		}
		channel.close();
		scanner.close();
	}

}
