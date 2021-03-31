package cn.thinking.rmi;

import java.rmi.Naming;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 15:40
 * @Description:
 */
public class RemoteClient {

    public static String client(String host, String inputStr) {
        try {
            /**
             * 查询并获得远程对象的存根
             * 也可以考虑使用dynamic class downloading，
             * 一旦找不到远程对象，可以从通过url动态下载class
             */
            MyRemote stub = (MyRemote) Naming.lookup(host);
            // 像在使用本地对象方法那样，调用远程方法
            return stub.sayHello(inputStr);
        } catch (Exception e) {
            System.out.println("Client exception :" + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
