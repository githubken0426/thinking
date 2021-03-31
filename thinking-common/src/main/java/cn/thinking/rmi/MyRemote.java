package cn.thinking.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程调用:remote method invocation
 * @author Administrator
 *
 */
public interface MyRemote extends Remote {
	/**
	 * remote method need to throws RemoteException
	 * @param args
	 * @return
	 * @throws RemoteException
	 */
	String sayHello(String args) throws RemoteException;
}
