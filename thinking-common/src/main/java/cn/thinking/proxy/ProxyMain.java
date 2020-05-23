package cn.thinking.proxy;

import cn.thinking.proxy.bean.Book;

public class ProxyMain {
	public static void main(String[] args) {
		Book book1 = BookFactory.getInstance(new ProxyInterceptor("boss"));
		book1.query();
		/*book1.create();
		book1.delete();*/
		book1.update(200);
		/*Book book2 = BookFactory.getInstance(new MyCglibProxy("kisi"));  
		book2.query();
		book2.create();
		book2.delete();
		book2.update(200);*/
	}
}
