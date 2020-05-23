package cn.thinking.signature.service;


import cn.thinking.signature.pojo.Client;
import cn.thinking.signature.pojo.SignatureData;

public interface SignatureService {
    boolean doCheck(Client client, SignatureData signatureData);

    String signatureString(Client client, SignatureData signatureData);
}
