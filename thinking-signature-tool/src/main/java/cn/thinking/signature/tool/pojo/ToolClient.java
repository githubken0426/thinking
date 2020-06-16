package cn.thinking.signature.tool.pojo;

import cn.thinking.signature.exception.SecurityKeyInitializationException;
import cn.thinking.signature.pojo.Client;
import cn.thinking.signature.utils.SignatureUtils;
import cn.thinking.signature.utils.EnvironmentUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ToolClient extends Client {

	/**
	 * 客户端请求加密的私钥key
	 */
	private PrivateKey clientPrivateKey;

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

	public PrivateKey getClientPrivateKey() {
		return clientPrivateKey;
	}

	public void setClientPrivateKey(PrivateKey clientPrivateKey) {
		this.clientPrivateKey = clientPrivateKey;
	}

	/**
	 * 解密服务端响应加密串
	 */
	private PublicKey serverPublicKey;

	public void setServerPublicKeyPath(String serverPublicKeyPath) {
		try {
			Path publicKeyPem = Paths.get(EnvironmentUtils.getEnvironment("APP_HOME_CONF") + serverPublicKeyPath);
			if (publicKeyPem.toFile().exists()) {
				this.serverPublicKey = SignatureUtils.exportPublicKey(publicKeyPem);
			} else {
				throw new SecurityKeyInitializationException("Key path not exist.");
			}
		} catch (Exception e) {
			throw new SecurityKeyInitializationException("Security key init failed", e);
		}
	}

	public PublicKey getServerPublicKey() {
		return serverPublicKey;
	}

	public void setServerPublicKey(PublicKey serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}
}
