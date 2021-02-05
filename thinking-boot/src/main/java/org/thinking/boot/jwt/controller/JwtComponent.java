package org.thinking.boot.jwt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.thinking.boot.jwt.Constants;
import org.thinking.boot.jwt.enumration.IssuerEunm;
import org.thinking.boot.jwt.manager.ClientManager;
import org.thinking.boot.jwt.pojo.JwtClient;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JwtComponent {
	@Value("${jwt.expirationTime}")
	private float expirationTime;
	@Autowired
	private ClientManager clientManager;
	@Autowired
	ResourceLoader resourceLoader;

	public String generatorToken(Map<String, String> map, IssuerEunm issuerEnum) throws Exception {
		JwtClaims claims = new JwtClaims();
		claims.setGeneratedJwtId(); // jwt id
		claims.setIssuer(issuerEnum.getIssuer());
		claims.setIssuedAtToNow();// jwt发布时间（当前时间）
		claims.setExpirationTimeMinutesInTheFuture(expirationTime);// jwt有效期
		JwtClient jwtClient = clientManager.getClientByIssuer(issuerEnum.getIssuer());
		claims.setClaim(jwtClient.getIssuerId(), map);

		JsonWebSignature signature = new JsonWebSignature();
		signature.setPayload(claims.toJson());
		signature.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		signature.setKey(this.getSecretKey(jwtClient));
		signature.setDoKeyValidation(false);
		return signature.getCompactSerialization();
	}

	private SecretKey getSecretKey(IssuerEunm issuerEnum) {
		JwtClient jwtClient = clientManager.getClientByIssuer(issuerEnum.getIssuer());
		return this.getSecretKey(jwtClient);
	}

	private SecretKey getSecretKey(JwtClient jwtClient) {
		Resource resource = resourceLoader.getResource("classpath:" + jwtClient.getKeyStoreFilePath());
		try (InputStream inputStream = resource.getInputStream()) {
			KeyStore keyStore = KeyStore.getInstance(Constants.JWT_KEYSTORE_TYPE);
			// 公钥
			keyStore.load(inputStream, jwtClient.getStorePass().toCharArray());
			// 私钥
			Key key = keyStore.getKey(jwtClient.getKeyAlias(), jwtClient.getKeyPass().toCharArray());
			return new SecretKeySpec(key.getEncoded(), Constants.JWT_KEYSTORE_ALGO);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean verifyToken(String token, IssuerEunm issuerEnum) {
		try {
			AlgorithmConstraints constraints = new AlgorithmConstraints(ConstraintType.WHITELIST,
					AlgorithmIdentifiers.HMAC_SHA256);
			JwtConsumer consumer = new JwtConsumerBuilder().setRequireJwtId().setRequireIssuedAt()
					.setRequireExpirationTime().setJwsAlgorithmConstraints(constraints)
					.setVerificationKey(this.getSecretKey(issuerEnum)).build();
			JwtContext context = consumer.process(token);
			System.out.println(context);
			return true;
		} catch (InvalidJwtException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifyToken(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			JwtClient jwtClient = clientManager.getClientByIssuer(claimsSet.getIssuer());
//			JWSVerifier verifier = new MACVerifier(jwtClient.getKeyStorePassword());

			Resource resource = resourceLoader.getResource("classpath:" + jwtClient.getKeyStoreFilePath());
			JWKSet keySet = JWKSet.load(resource.getFile());
			JWK jwk = keySet.getKeyByKeyId(claimsSet.getJWTID());
			ECKey ecKey = ECKey.parse(jwk.toJSONObject());
			JWSVerifier verifier = new ECDSAVerifier(ecKey);
			if (signedJWT.verify(verifier)) {
				// 判断token是否过期
				Calendar now = Calendar.getInstance();
				if (now.after(claimsSet.getExpirationTime())) {
					return false;
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> dumpToken(String token, String issuer) {
		try {
			JwtConsumer consumer = new JwtConsumerBuilder().setSkipAllValidators().setDisableRequireSignature()
					.setSkipSignatureVerification().build();
			JwtContext context = consumer.process(token);
			JwtClaims claims = context.getJwtClaims();
			JwtClient jwtClient = clientManager.getClientByIssuer(issuer);
			return (Map<String, String>) claims.getClaimsMap().get(jwtClient.getIssuerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> dumpToken(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			String issuer = claimsSet.getIssuer();
			JwtClient jwtClient = clientManager.getClientByIssuer(issuer);
			return (Map<String, String>) claimsSet.getClaims().get(jwtClient.getIssuerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

	public void test() throws JOSEException {
		RSAKey rsaJWK = new RSAKeyGenerator(2048).keyID("123").generate();
		RSAKey rsaPublicJWK = rsaJWK.toPublicJWK();
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(rsaJWK);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject("alice").issuer("https://c2id.com")
				.expirationTime(new Date(new Date().getTime() + 60 * 1000)).build();
		SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
				claimsSet);
		// Compute the RSA signature
		signedJWT.sign(signer);

		// To serialize to compact form, produces something like
		// eyJhbGciOiJSUzI1NiJ9.SW4gUlNBIHdlIHRydXN0IQ.IRMQENi4nJyp4er2L
		// mZq3ivwoAjqa1uUkSBKFIX7ATndFF5ivnt-m8uApHO4kfIFOrW7w2Ezmlg3Qd
		// maXlS9DhN0nUk_hGI3amEjkKd0BWYCB8vfUbUv0XGjQip78AI4z1PrFRNidm7
		// -jPDm5Iq0SZnjKjCNS5Q15fokXZc8u0A
		String s = signedJWT.serialize();
		System.out.println(s);
	}
}
