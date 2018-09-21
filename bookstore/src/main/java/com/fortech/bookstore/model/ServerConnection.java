package com.fortech.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fortech.bookstore.util.annotation.URL;

@Entity
public class ServerConnection {

	@Id
	@GeneratedValue
	private Long id;

	private String uri;

	private String ftpUri;

	public ServerConnection() {
		super();
	}

	public ServerConnection(@URL(protocol = "ftp", port = 22) String uri,
			@URL(protocol = "https", host = "www.cdbooks.com") String ftpUri) {
		super();
		this.uri = uri;
		this.ftpUri = ftpUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFtpUri() {
		return ftpUri;
	}

	public void setFtpUri(String ftpUri) {
		this.ftpUri = ftpUri;
	}

}
