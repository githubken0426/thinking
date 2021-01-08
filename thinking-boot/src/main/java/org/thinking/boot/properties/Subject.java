package org.thinking.boot.properties;

public class Subject {
	private String host;
	private String port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + (host == null ? 0 : host.hashCode());
		hash = hash * 31 + (port == null ? 0 : port.hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!(obj instanceof Subject))
			return false;

		Subject sub = (Subject) obj;
		boolean hostB = host != null && host.equals(sub.host);
		boolean protB = port != null && port.equals(sub.port);
		return hostB && protB;
	}

}
