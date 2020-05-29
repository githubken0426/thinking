package cn.thinking.signature.utils;

import cn.thinking.signature.constans.Constant;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class SignatureUtils {
	/**
	 * 根据私钥和字符串生成签名字符串
	 *
	 * @return java.lang.String
	 */
	public static String generateSignature(PrivateKey privateKey, String content) {
		try {
			byte[] digestContent = SignatureUtils.getHashValue(content);
			Signature sign = Signature.getInstance(Constant.SIGNATURE_ALGORITHM);
			sign.initSign(privateKey);
			sign.update(digestContent);
			byte[] signedContent = sign.sign();
			return new String(Base64.getEncoder().encode(signedContent));
		} catch (Exception ex) {
			throw new SecurityException("RSA sign error.", ex);
		}
	}

	/**
	 * 根据私钥和字节数组生成签名字符串
	 *
	 * @return java.lang.String
	 */
	public static String generateSignature(PrivateKey privateKey, byte[] content) {
		try {
			byte[] digestContent = SignatureUtils.getHashValueByByte(content);
			Signature sign = Signature.getInstance(Constant.SIGNATURE_ALGORITHM);
			sign.initSign(privateKey);
			sign.update(digestContent);
			byte[] signedContent = sign.sign();
			return new String(Base64.getEncoder().encode(signedContent));
		} catch (Exception ex) {
			throw new SecurityException("RSA sign error.", ex);
		}
	}

	/**
	 * 公钥验证字符串签名是否正确
	 *
	 * @return java.lang.boolean
	 */
	public static boolean isSignatureValidated(PublicKey publicKey, String content, String signature) {
		try {
			byte[] digestContent = SignatureUtils.getHashValue(content);
			Signature sign = Signature.getInstance(Constant.SIGNATURE_ALGORITHM);
			sign.initVerify(publicKey);
			sign.update(digestContent);
			return sign.verify(Base64.getDecoder().decode(signature));
		} catch (Exception ex) {
			throw new SecurityException(Constant.SIGN_VERIFICATION_FAIL);
		}
	}

	/**
	 * 公钥验证字节数组签名是否正确
	 *
	 * @return java.lang.boolean
	 */
	public static boolean isSignatureValidated(PublicKey publicKey, byte[] content, String signature) {
		try {
			byte[] digestContent = SignatureUtils.getHashValueByByte(content);

			Signature sign = Signature.getInstance(Constant.SIGNATURE_ALGORITHM);
			sign.initVerify(publicKey);
			sign.update(digestContent);
			return sign.verify(Base64.getDecoder().decode(signature));
		} catch (Exception ex) {
			throw new SecurityException(Constant.SIGN_VERIFICATION_FAIL);
		}
	}

	/**
	 * 获取私钥key
	 *
	 * @return PrivateKey
	 */
	public static PrivateKey exportPrivateKey(Path path) throws Exception {
		byte[] keyContent = Base64.getMimeDecoder().decode(SignatureUtils.getBase64KeyContent(path));
		KeyFactory kf = KeyFactory.getInstance(Constant.RSA_ALGORITHM_KEY);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyContent);
		return kf.generatePrivate(keySpec);
	}

	/**
	 * 获取公钥key
	 *
	 * @return PublicKey
	 */
	public static PublicKey exportPublicKey(Path path) throws Exception {
		byte[] keyContent = Base64.getMimeDecoder().decode(SignatureUtils.getBase64KeyContent(path));
		KeyFactory kf = KeyFactory.getInstance(Constant.RSA_ALGORITHM_KEY);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyContent);
		return kf.generatePublic(keySpec);
	}

	private static String getBase64KeyContent(Path path) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(path)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (StringUtils.isNotBlank(line) && !StringUtils.contains(line, "-")) {
					sb.append(line).append(Constant.LINE_SEPARATOR);
				}
			}
		}
		return sb.toString();
	}

	private static byte[] getHashValue(String content) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(Constant.HASH_ALGORITHM);
		messageDigest.update(content.getBytes(Constant.CONTENT_ENCODE));
		return messageDigest.digest();
	}

	private static byte[] getHashValueByByte(byte[] content) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(Constant.HASH_ALGORITHM);
		messageDigest.update(content);
		return messageDigest.digest();
	}

	/**
	 * 通过公钥进行签名
	 *
	 * @return java.lang.String
	 * @author Sunny
	 * @date 2019/7/3 13:54
	 * @version V1.0
	 */
	public static String encryptionByPublicKey(PublicKey publicKey, String body) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(body.getBytes());
//            return Base64.encodeBase64String(result);
		} catch (Exception e) {
			throw new SecurityException(Constant.SIGN_FAIL);
		}
		return null;
	}
}
