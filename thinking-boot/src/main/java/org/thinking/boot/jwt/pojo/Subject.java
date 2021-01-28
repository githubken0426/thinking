package org.thinking.boot.jwt.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subject {
	@JsonProperty("user_info")
	private String userInfo;
	@JsonProperty("client_id")
	private String clientId;

	public Subject() {

	}

	public Subject(String userInfo, String clientId) {
		this.userInfo = userInfo;
		this.clientId = clientId;

	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
