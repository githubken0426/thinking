package org.thinking.boot.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpRequestFactoryBuilder {
    
    private static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 30000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT = 30000;

    public static ClientHttpRequestFactory build() {
        return build(DEFAULT_CONNECTION_REQUEST_TIMEOUT, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, HttpClients.createSystem());
    }
    
    public static ClientHttpRequestFactory build(HttpClient httpClient) {
        return build(DEFAULT_CONNECTION_REQUEST_TIMEOUT, DEFAULT_CONNECT_TIMEOUT, DEFAULT_READ_TIMEOUT, httpClient);
    }
    
    public static ClientHttpRequestFactory build(int connectionRequestTimeout, int connectTimeout, int readTimeout) {
        return build(connectionRequestTimeout, connectTimeout, readTimeout, HttpClients.createSystem());
    }
    
    public static ClientHttpRequestFactory build(int connectionRequestTimeout, int connectTimeout, int readTimeout, HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);
        return httpComponentsClientHttpRequestFactory;
    }

}
