package cn.thinking.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
	public static void main(String[] args) throws IOException {
		fullDemo();
	}
	/**
	 * Selector（选择器，multiplexing)是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
	   *   这样，一个单独的线程可以管理多个channel， 相比使用多个线程，避免了线程上下文切换带来的开销。
	   *  与Selector一起使用时，Channel必须处于非阻塞模式下（否继承了抽象类SelectableChannel）。
	   *  这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
	 * 
	 * @throws IOException
	 */
	static void fullDemo() throws IOException {
		/**
		 * step 1: 创建Selector，调用Selector.open()方法
		 */
		Selector selector = Selector.open();
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
//		SocketChannel channel = SocketChannel.open();
//		channel.connect(new InetSocketAddress("www.baidu.com", 80));
		channel.configureBlocking(false);
		/**
		   *  这四种事件用SelectionKey的四个常量来表示： 
		 * SelectionKey.OP_CONNECT,SelectionKey.OP_ACCEPT,SelectionKey.OP_READ,SelectionKey.OP_WRITE
		 * 
		   *   并非一定要支持所有的四种操作。比如服务器通道ServerSocketChannel支持Accept接受操作，而SocketChannel客户端通道则不支持。
		   *   可以通过通道上的validOps()方法，来获取特定通道下所有支持的操作集合。
		 */
		int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;// 同时指定多个事件
		
		/**
		 * step 2:Channel注册到Selector
		 * 
		   * 注册时候，指定channel的哪些操作，是Selector感兴趣的。
		   * 通道触发了一个事件意思是该事件已经就绪，一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”
		 * register（）方法返回SelectionKey，包含以下属性：interest集合,ready集合 Channel,Selector附加的对象（可选）
		 */
		SelectionKey selectionKey = channel.register(selector, interestSet);
		/**
		 * step 3:通过Selector选择通道：selector.select()
		 * select()方法返回的int值，表示有多少通道已经就绪，更准确的说，是自前一次select方法以来到这一次select方法之间的时间段上，有多少通道变成就绪状态。
		 * 
		 * select()方法返回的int值表示有多少通道已经就绪(阻塞到至少有一个通道在你注册的事件上就绪了)
		 * select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。
		 * selectNow()不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）
		 * 
		   *  调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。
		   *  用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭
		 */
		int readyChannels = selector.select();
//		selector.wakeup();
//		selector.close();
		/**
		 * step 4：SelectionKey
		 */
		Set<SelectionKey> selectedKeys = selector.selectedKeys();
		Iterator<SelectionKey> it = selectedKeys.iterator();
		while (it.hasNext()) {
			SelectionKey selection = it.next();
			if (selection.isAcceptable()) {
				// a connection was accepted by a ServerSocketChannel.
				System.out.println(selection.isAcceptable());
			} else if (selection.isConnectable()) {
				// a connection was established with a remote server.
				System.out.println(selection.isConnectable());
			} else if (selection.isReadable()) {
				// a channel is ready for reading
				System.out.println(selection.isReadable());
			} else if (selection.isWritable()) {
				// a channel is ready for writing
				System.out.println(selection.isWritable());
			}
			/**
			 * remove()：Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
			  *  下次该通道变成就绪时，Selector会再次将其放入已选择键集中。
			 */
			it.remove();
		}

		/**
		 * interest集合
		 * “位与”操作interest 集合和给定的SelectionKey常量，可以确定某个确定的事件是否在interest 集合中
		 */
		int interest = selectionKey.interestOps();
		boolean isInterestedInAccept  = ((interest & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT);
		/**
		 * ready集合：
		   *  是通道已经准备就绪的操作的集合。在一次选择(Selection)之后，你会首先访问这个ready set.
		   *  可以通过位与操作检测channel中什么事件或操作已经就绪
		   *  或使用以下四种方法检测
		 */
		int readySet = selectionKey.readyOps();
		selectionKey.isAcceptable();
		selectionKey.isConnectable();
		selectionKey.isReadable();
		selectionKey.isWritable();

		/**
		 * Channel和Selector
		 */
		Channel channel1 = selectionKey.channel();
		Selector selector1 = selectionKey.selector();
		/**
		 * attachment：
		   *  可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道
		 */
		selectionKey.attach(new Object());
		Object attachedObj = selectionKey.attachment();
		//Selector注册Channel的时候附加对象
		channel.register(selector, SelectionKey.OP_READ, attachedObj);


	}
}
