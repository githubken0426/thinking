package cn.thinking.signature.tool.service;


import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.tool.pojo.SignatureResponse;
import cn.thinking.signature.tool.pojo.ToolClient;

public interface SignatureToolService {
    String generatorClientToken(String clientId);

    SignatureResponse generateSignature(String clientId, SignatureData signatureData);

    ToolClient getClientByClientId(String clientId);
}
