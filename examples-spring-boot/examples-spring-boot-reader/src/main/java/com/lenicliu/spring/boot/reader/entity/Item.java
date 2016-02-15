package com.lenicliu.spring.boot.reader.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "tb_item")
public class Item {

	@Id
	@GeneratedValue
	private Long	id;
	@Column(length = 200, unique = true, nullable = false)
	private String	guid;
	@Column(length = 200, nullable = false)
	private String	title;
	@Column(length = 200, nullable = false)
	private String	link;
	@Column(length = 100)
	private String	author;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date	created;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}