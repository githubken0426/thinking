package org.thinking.boot.el;

import java.util.List;

public class JsonBean {
	private String name;
	private String version;
	private String author;
	private String description;
	private List<String> platforms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

	public String toString() {
		return name + "," + version + "," + author + "," + description + "," + platforms;
	}
}
