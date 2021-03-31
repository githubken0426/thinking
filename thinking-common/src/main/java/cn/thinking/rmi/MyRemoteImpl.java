package cn.thinking.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    private static final long serialVersionUID = 1L;

    /**
     * UnicastRemoteObject声明了RemoteException
     * 所以需要throw RemoteException
     *
     * @throws RemoteException
     */
    public MyRemoteImpl() throws RemoteException {
        super();
    }

    /**
     * 不需要 throws RemoteException
     *
     * @return
     */
    @Override
    public String sayHello(String args) {
        return args;
    }

}
