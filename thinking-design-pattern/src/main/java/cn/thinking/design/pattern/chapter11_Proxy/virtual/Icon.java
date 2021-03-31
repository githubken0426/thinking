package cn.thinking.design.pattern.chapter11_Proxy.virtual;

import javax.swing.text.html.ImageView;

public interface Icon {
	int getIconWidth();
    int getIconHeight();
    void paintIcon(ImageView imageView);
}
