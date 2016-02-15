package com.lenicliu.spring.boot.reader.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "tb_link")
public class Link implements Serializable {
	private static final long	serialVersionUID	= -7312122013042959361L;
	@Id
	@GeneratedValue
	private Long				id;
	@Column(length = 100, nullable = false)
	private String				title;
	@Column(length = 200, nullable = false)
	private String				url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}