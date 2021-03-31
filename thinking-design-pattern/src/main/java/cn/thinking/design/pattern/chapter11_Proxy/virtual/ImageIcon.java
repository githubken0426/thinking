package cn.thinking.design.pattern.chapter11_Proxy.virtual;

import javax.swing.text.html.ImageView;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.ImageView;

public class ImageIcon implements Icon {
	BitMap bitmap;

	public ImageIcon(BitMap bitmap) {
		this.bitmap = bitmap;
	}

	@Override
	public int getIconWidth() {
		if (bitmap != null) {
			return bitmap.getWidth();
		}
		return 0;
	}

	@Override
	public int getIconHeight() {
		if (bitmap != null) {
			return bitmap.getHeight();
		}
		return 0;
	}

	@Override
	public void paintIcon(ImageView imageView) {
		if (imageView != null && bitmap != null) {
			//imageView.setImageBitmap(bitmap);
		}
	}
}
