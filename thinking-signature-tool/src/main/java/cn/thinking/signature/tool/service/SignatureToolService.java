package cn.thinking.signature.tool.service;


import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.tool.pojo.Client;
import cn.thinking.signature.tool.pojo.SignatureResponse;

public interface SignatureToolService {
    String generatorClientToken(String clientId);

    SignatureResponse generateSignature(String clientId, SignatureData signatureData);

    Client getClientByClientId(String clientId);
}
