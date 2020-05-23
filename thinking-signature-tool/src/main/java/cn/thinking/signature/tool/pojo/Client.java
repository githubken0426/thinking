package cn.thinking.signature.tool.pojo;

import cn.thinking.signature.exception.SecurityKeyInitializationException;
import cn.thinking.signature.utils.SignatureUtils;
import cn.thinking.signature.utils.EnvironmentUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;

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
     * 响应是加密的私钥key
     */
    private PrivateKey clientPrivateKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClientPrivateKeyPath(String clientPrivateKeyPath) {
        try {
            String envPath = EnvironmentUtils.getEnvironment("APP_HOME_CONF");
            Path privateKeyPem = Paths.get(envPath + clientPrivateKeyPath);
            if (Files.exists(privateKeyPem)) {
                this.clientPrivateKey = SignatureUtils.exportPrivateKey(privateKeyPem);
            } else {
                throw new SecurityKeyInitializationException("Key path not exist.");
            }
        } catch (Exception e) {
            throw new SecurityKeyInitializationException("Security key init failed", e);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PrivateKey getClientPrivateKey() {
        return clientPrivateKey;
    }

    public void setClientPrivateKey(PrivateKey clientPrivateKey) {
        this.clientPrivateKey = clientPrivateKey;
    }
}
