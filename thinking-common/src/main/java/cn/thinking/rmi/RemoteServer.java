package cn.thinking.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Auther: kun.f.wang
 * @Date: 2/11/2019 16:05
 * @Description:
 */
public class RemoteServer {

    public static void server(String host) {
        try {
            // 实例化远程对象，同时导出了该对象
            MyRemote server = new MyRemoteImpl();
            LocateRegistry.createRegistry(7600);
            Naming.rebind(host, server);
            // 通告服务端已准备好了
            System.out.println("System already!");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("在建立远程连接的情况出现了异 常" + e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
