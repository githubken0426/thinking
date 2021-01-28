package org.thinking.boot.http;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.bind.DatatypeConverter;

public class TlsConfig {
	public static byte[] toByteArray(String filename) {
		try (FileChannel channel = new RandomAccessFile(filename, "r").getChannel();) {
			MappedByteBuffer byteBuffer = channel.map(MapMode.READ_ONLY, 0, channel.size()).load();
			byte[] result = new byte[(int) channel.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SSLSocketFactory getSocketFactoryPEM(String pemPath) throws Exception {
		byte[] certAndKey = toByteArray(pemPath);
		byte[] certBytes = parseDERFromPEM(certAndKey, "-----BEGIN CERTIFICATE-----", "-----END CERTIFICATE-----");
		byte[] keyBytes = parseDERFromPEM(certAndKey, "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----");

		X509Certificate cert = generateCertificateFromDER(certBytes);
		RSAPrivateKey key = generatePrivateKeyFromDER(keyBytes);

		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(null);
		keystore.setCertificateEntry("cert-alias", cert);
		keystore.setKeyEntry("key-alias", key, "<password>".toCharArray(), new Certificate[] { cert });

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keystore, "<password>".toCharArray());
		KeyManager[] km = kmf.getKeyManagers();
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(km, null, null);
		return context.getSocketFactory();
	}

	public static byte[] parseDERFromPEM(byte[] pem, String beginDelimiter, String endDelimiter) {
		String data = new String(pem);
		String[] tokens = data.split(beginDelimiter);
		tokens = tokens[1].split(endDelimiter);
		return DatatypeConverter.parseBase64Binary(tokens[0]);
	}

	public static RSAPrivateKey generatePrivateKeyFromDER(byte[] keyBytes)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		return (RSAPrivateKey) factory.generatePrivate(spec);
	}

	public static X509Certificate generateCertificateFromDER(byte[] certBytes) throws CertificateException {
		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		Certificate certificate = factory.generateCertificate(new ByteArrayInputStream(certBytes));
		return (X509Certificate) certificate;
	}
}
