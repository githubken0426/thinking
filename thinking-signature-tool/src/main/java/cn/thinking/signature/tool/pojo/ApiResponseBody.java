package cn.thinking.signature.tool.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseBody<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1318935795468276793L;

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponseBody() {
        super();
    }

    public ApiResponseBody(final String code) {
        super();
        this.code = code;
    }

    public ApiResponseBody(final String code, final String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApiResponseBody(final String code, final T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ApiResponseBody(final String code, final String message, final T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
