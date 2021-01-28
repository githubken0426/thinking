package cn.thinking.external;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientConfig {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, KeyManagementException, ClientProtocolException, IOException {
		HttpPost post = new HttpPost("https://www.baidu.com");
		HttpClient client = getCloseableHttpClient();
		// HttpResponseProxy implements CloseableHttpResponse
		HttpResponse response = client.execute(post);
		System.out.println(response);
		HttpEntity entity = response.getEntity();
		/**
		 * DecompressingEntity：[Content-Type: text/html,Content-Encoding:gzip,Content-Length: 1145,Chunked: false]
		 */
		System.out.println(entity);
	}

	private static HttpClient getCloseableHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
		X509TrustManager trustManager = new X509TrustManager() {
			//检查客户端的证书是否可信
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}
			//检查服务器的证书是否可信
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}
			/**
			 * getAcceptedIssuers():将从(配置的/可用的)信任库返回证书颁发机构（CA）的列表.
			 * 
			 * 通常，在打开https连接时，如果此方法返回X509Certificate [0]，则此方法将在内部调用，那么这意味着Trust Store中不存在可信的CA，即您不信任任何人;
			 * 因此checkServerTrusted或checkClientTrusted抛出CertificateException，取决于被调用的是.
			 * 如果你编写自己的x509TrustManager实现，那么理想情况下你应该在实现checkClientTrusted和checkServerTrusted时
			 * 调用getAcceptedissuers，但这完全取决于你在实现中想做什么。
			 * getAcceptedIssuers更像是一种私有方法，没有外部依赖;
			 */
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				try (InputStream inStream = new FileInputStream("fileName-of-cert")) {
					CertificateFactory cf = CertificateFactory.getInstance("X.509");
					X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
					return new X509Certificate[] { cert };
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				}
				return null;
			}

		};

		SSLContext sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
		sslContext.init(null, new TrustManager[] { trustManager }, null);

		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
				sslContext,
				new String[] { "TLSv1.2" }, 
				null, 
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", socketFactory)
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);

		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(1 * 60 * 1000)
				.setSocketTimeout(3 * 60 * 1000)
				.build();
		return HttpClients.custom()
				.setDefaultRequestConfig(config)
				.setConnectionManager(connManager)
				.build();
	}
}
