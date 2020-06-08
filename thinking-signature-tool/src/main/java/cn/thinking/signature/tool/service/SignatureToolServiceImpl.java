package cn.thinking.signature.tool.service;

import cn.thinking.signature.pojo.SignatureData;
import cn.thinking.signature.service.SignatureService;
import cn.thinking.signature.tool.pojo.Client;
import cn.thinking.signature.tool.pojo.SignatureResponse;
import cn.thinking.signature.tool.utils.AuthUtils;
import cn.thinking.signature.utils.SignatureUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "signature")
public class SignatureToolServiceImpl implements SignatureToolService {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private SignatureService signatureService;

    List<Client> clients;

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String generatorClientToken(String clientId) {
        Client client = getClientByClientId(clientId);
        if (client == null) {
            return null;
        }
        return AuthUtils.generatorAuthorizationToken(client.getName(), client.getPassword());
    }

    @Override
    public SignatureResponse generateSignature(String clientId, SignatureData signatureData) {
        SignatureResponse response = new SignatureResponse();
        Client client = getClientByClientId(clientId);
        if (client == null) {
            return response;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(signatureData.getHttpMethod());
        sb.append(signatureData.getUri());
        if (StringUtils.isNotEmpty(signatureData.getHttpQueryString())) {
            sb.append(signatureData.getHttpQueryString());
        }
        String httpBody = signatureData.getHttpBody();
        if (StringUtils.isNotEmpty(httpBody)) {
            sb.append(httpBody);
        }
        String content = sb.toString();
        logger.info("加密字符串:{}", content);
        String signature = SignatureUtils.generateSignature(client.getClientPrivateKey(), content);
        logger.info("加密结果:{}", signature);
        response.setEncodeString(content);
        response.setSignature(signature);
        return response;
    }

    @Override
    public Client getClientByClientId(String clientId) {
        if (StringUtils.isEmpty(clientId)) {
            return null;
        }
        for (Client client : this.clients) {
            if (client.getName().equals(clientId)) {
                return client;
            }
        }
        return null;
    }
}
