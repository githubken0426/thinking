package cn.thinking.signature.manager;


import cn.thinking.signature.pojo.Client;

public interface ClientManager {
    Client getClientByToken(String token);

    Client getClientByUsername(String userName);
}
