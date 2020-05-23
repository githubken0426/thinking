package cn.thinking.signature.tool.utils;

import org.springframework.http.HttpHeaders;

import java.util.Base64;

public class AuthUtils {

    public static HttpHeaders createBasicAuthorizationHeaders(String userName, String password) {
        String auth = userName + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String basicAuthString = "Basic " + encodedAuth;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, basicAuthString);
        return httpHeaders;
    }

    public static String generatorAuthorizationToken(String userName, String password) {
        String auth = userName + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        return "Basic " + encodedAuth;
    }
}
