package cn.thinking.signature.tool.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignatureRequest {
    private String requestUrl;
    private String requestMethod;
    private String requestBody;
    private String clientId;
}
