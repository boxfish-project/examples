package com.lenicliu.jboot.showcase.task.domain;

import java.io.Serializable;

public class Task implements Serializable {

	private static final long	serialVersionUID	= -4838969984097273029L;
	private Long				id;
	private Long				uid;
	private String				title;
	private String				due;
	private String				state;

	public enum State {
		OPENING, WORKING, COMPLETED;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}