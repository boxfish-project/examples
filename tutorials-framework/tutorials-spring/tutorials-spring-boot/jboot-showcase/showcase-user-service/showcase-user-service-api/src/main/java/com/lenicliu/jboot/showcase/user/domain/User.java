package com.lenicliu.jboot.showcase.user.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long	serialVersionUID	= -2072874806959718500L;

	private Long				id;
	private String				username;
	private String				password;
	private Long				created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
}