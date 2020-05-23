package cn.thinking.signature.pojo;

import cn.thinking.signature.exception.SecurityKeyInitializationException;
import cn.thinking.signature.utils.EnvironmentUtils;
import cn.thinking.signature.utils.SignatureUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Client {
    /**
     * 渠道名称
     */
    private String name;
    /**
     * 渠道密码
     */
    private String password;
    /**
     * 请求是解密的公钥key
     */
    private PublicKey clientPublicKey;
    /**
     * 响应是加密的私钥key
     */
    private PrivateKey serverPrivateKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PublicKey getClientPublicKey() {
        return clientPublicKey;
    }

    public void setClientPublicKey(PublicKey clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }

    public PrivateKey getServerPrivateKey() {
        return serverPrivateKey;
    }

    public void setServerPrivateKey(PrivateKey serverPrivateKey) {
        this.serverPrivateKey = serverPrivateKey;
    }

    public void setClientPublicKeyPath(String clientPublicKeyPath) {
        try {
            Path publicKeyPem = Paths.get(EnvironmentUtils.getEnvironment("APP_HOME_CONF") + clientPublicKeyPath);
            if (publicKeyPem.toFile().exists()) {
                this.clientPublicKey = SignatureUtils.exportPublicKey(publicKeyPem);
            } else {
                throw new SecurityKeyInitializationException("Key path not exist.");
            }
        } catch (Exception e) {
            throw new SecurityKeyInitializationException("Security key init failed", e);
        }
    }

    public void setServerPrivateKeyPath(String serverPrivateKeyPath) {
        try {
            Path privateKeyPem = Paths.get(EnvironmentUtils.getEnvironment("APP_HOME_CONF") + serverPrivateKeyPath);
            if (Files.exists(privateKeyPem)) {
                this.serverPrivateKey = SignatureUtils.exportPrivateKey(privateKeyPem);
            } else {
                throw new SecurityKeyInitializationException("Key path not exist.");
            }
        } catch (Exception e) {
            throw new SecurityKeyInitializationException("Security key init failed", e);
        }
    }

}
