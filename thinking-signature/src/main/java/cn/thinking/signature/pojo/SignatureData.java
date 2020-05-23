package cn.thinking.signature.pojo;

public class SignatureData {
    private String httpMethod;
    private String uri;
    private String httpQueryString;
    private String httpLocation;
    private StringBuffer httpBody = new StringBuffer();
    private String httpStatus;
    private String signature;

    public String getHttpQueryString() {
        return httpQueryString;
    }

    public void setHttpQueryString(String httpQueryString) {
        this.httpQueryString = httpQueryString;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpLocation() {
        return httpLocation;
    }

    public void setHttpLocation(String httpLocation) {
        this.httpLocation = httpLocation;
    }

    public String getHttpBody() {
        return httpBody.toString();
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = new StringBuffer(httpBody);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void addItemToHttpBody(String item) {
        this.httpBody.append(item);
    }

    @Override
    public String toString() {
        return "SignatureData{" +
                "httpMethod='" + httpMethod + '\'' +
                ", uri='" + uri + '\'' +
                ", httpQueryString='" + httpQueryString + '\'' +
                ", httpLocation=" + httpLocation +
                ", httpBody='" + httpBody + '\'' +
                ", httpStatus='" + httpStatus + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

}
