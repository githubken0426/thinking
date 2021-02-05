package org.thinking.volume21.concurrence.chapter4._03Interrupted._method2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NIOInterrupted {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exe=Executors.newCachedThreadPool();
		ServerSocket server=new ServerSocket(8080);
		InetSocketAddress input=new InetSocketAddress("localhost",8080);
		
		SocketChannel sc1=SocketChannel.open(input);
//		SocketChannel sc2=SocketChannel.open(input);
		Future<?> f=exe.submit(new NIOBlocked(sc1));
//		exe.execute(new NIOBlocked(sc2));
		exe.shutdown();
		
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		
		TimeUnit.SECONDS.sleep(1);
//		sc2.close();
		
		server.close();
	}

}
