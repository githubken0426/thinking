package org.thinking.boot.http;

import org.springframework.http.HttpStatus;

public class RestServiceException extends Exception {

    private static final long serialVersionUID = -8653095517072001035L;
    
    private HttpStatus statusCode;
    
    private String statusText;
    
    private String responseBody;
    
    public RestServiceException(String message, HttpStatus statusCode, String statusText, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseBody = responseBody;
    }
    
    public RestServiceException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public RestServiceException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getResponseBody() {
        return responseBody;
    }

}
