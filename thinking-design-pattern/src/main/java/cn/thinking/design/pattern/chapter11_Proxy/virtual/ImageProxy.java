package cn.thinking.design.pattern.chapter11_Proxy.virtual;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.html.ImageView;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;

/**
 * 虚拟代理模式(Virtual Proxy)是一种节省内存的技术，它建议创建那些占用大量内存或处理复杂的对象时，把创建这类对象推迟到使用它的时候。 优点：
 * 这种方法的优点是，在应用程序启动时，由于不需要创建和装载所有的对象，因此加速了应用程序的启动。 缺点:
 * 因为不能保证特定的应用程序对象被创建，在访问这个对象的任何地方，都需要检测确认它不是空(null)。也就是，这种检测的时间消耗是最大的缺点。
 * 
 * @author kun.f.wang
 */
public class ImageProxy implements Icon {
	ImageIcon imageIcon; // 这是加载后显示出来的真正图像
	URL imageURL; // 真正图像的URL
	boolean retrieving = false;
	BitMap bitmap;// 占位图
	ImageView imageView;// 显示图片的容器

	@SuppressWarnings("rawtypes")
	private Handler refresh = new Handler() {
		@Override
		public boolean handleMessage(MessageContext context) {
			ImageProxy.this.paintIcon(imageView);
			return false;
		}

		@Override
		public boolean handleFault(MessageContext context) {
			return false;
		}

		@Override
		public void close(MessageContext context) {
		}
	};

	public ImageProxy(URL url, BitMap bitmap) {
		this.imageURL = url;
		this.bitmap = bitmap;

	}

	@Override
	public int getIconWidth() {
		if (imageIcon != null) {
			return imageIcon.getIconWidth();
		}
		return 800;
	}

	@Override
	public int getIconHeight() {
		if (imageIcon != null) {
			return imageIcon.getIconHeight();
		}
		return 600;
	}

	@Override
	public void paintIcon(ImageView imageView) {
		this.imageView = imageView;
		if (imageIcon != null) {
			imageIcon.paintIcon(imageView);
			return;
		}
		// imageView.setImageBitmap(bitmap);
		if (retrieving) {
			return;
		}
		retrieving = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (imageURL != null) {
					try {
						HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
						conn.setDoInput(true);
						conn.connect();
						InputStream is = conn.getInputStream();
						BitMap bitmap1 = BitMapFactory.decodeStream(is);
						imageIcon = new ImageIcon(bitmap1);
						is.close();
						// refresh.sendEmptyMessage(1); // 图像加载完成后，自动刷新
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
