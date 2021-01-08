package org.thinking.boot.jwt.enumration;

public enum IssuerEunm {
	SINA("sina", "sinaId"), BAIDU("baidu", "baiduId");

	private String issuer;
	private String issuerId;

	IssuerEunm(String issuer, String issuerId) {
		this.issuer = issuer;
		this.issuerId = issuerId;
	}

	public static IssuerEunm of(String issuer) {
		for (IssuerEunm client : IssuerEunm.values()) {
			if (client.getIssuer().equals(issuer)) {
				return client;
			}
		}
		return null;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

}
