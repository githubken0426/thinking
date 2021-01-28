package cn.thinking.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpKeyStoreValidate {
	// 客户端证书路径，用了本地绝对路径，需要修改
	private final static String PFX_PATH = "C:\\Development\\deployment\\ssl\\ca-demo\\client.p12";
	private final static String PFX_PWD = "demo"; // 客户端证书密码及密钥库密码

	public static String sslRequestGet(String url) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		try (InputStream instream = new FileInputStream(new File(PFX_PATH));) {
			// 这里就指的是KeyStore库的密码
			keyStore.load(instream, PFX_PWD.toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, PFX_PWD.toCharArray()).build();
			SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
					sslcontext, 
					new String[] { "TLSv1" } // supportedProtocols
					, null // supportedCipherSuites
					, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslFactory).build();
			HttpGet httpget = new HttpGet(url);
			// httpost.addHeader("Connection", "keep-alive");// 设置一些heander等
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			// 返回结果
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			EntityUtils.consume(entity);
			return jsonStr;
		}
	}
	
	
	@SuppressWarnings("unused")
	private PrivateKey getPrivateKey(String path) throws Exception {
		Base64 base64 = new Base64();
		byte[] buffer = base64.decode("");
	 
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

}
