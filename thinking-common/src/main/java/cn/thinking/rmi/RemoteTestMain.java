package cn.thinking.rmi;

import java.util.Scanner;

/**
 * @Auther: ken.wangTJ
 * @Date: 3/20/2019 15:06
 * @Description:
 */
public class RemoteTestMain {
    final static String host = "rmi://127.0.0.1:7600/sayHello";

    public static void main(String[] args) {
        RemoteServer.server(host);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入：");
        String inputStr = sc.nextLine();
        String result = RemoteClient.client(host, inputStr);
        System.out.println(result);
    }

}
