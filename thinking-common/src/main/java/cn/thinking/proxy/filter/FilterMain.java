package cn.thinking.proxy.filter;


import cn.thinking.proxy.bean.Book;
import cn.thinking.proxy.ProxyInterceptor;

public class FilterMain {
    public static void main(String[] args) {
        Book book1 = FilterFactory.getInstanceByFilter(new ProxyInterceptor("boss"));
        book1.create();
        book1.query();
        book1.delete();
        //update的返回值锁定为888
        System.out.println(book1.update(100));
        System.out.println("------------------------------------------------");

        Book book2 = FilterFactory.getInstanceByFilter(new ProxyInterceptor("kisi"));
        book2.create();
        book2.delete();
        book2.query();
        //update的返回值锁定为888
        System.out.println(book2.update(200));
    }
}
