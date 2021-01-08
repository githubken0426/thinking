package org.thinking.boot.properties;

public class Client extends Subject {
	private String maxActive;

	public String getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}
	
	public String toString() {
		return "[Client] host:" + this.getHost() + ",port:" + this.getPort() + ",maxActive:" + maxActive;
	}
}
