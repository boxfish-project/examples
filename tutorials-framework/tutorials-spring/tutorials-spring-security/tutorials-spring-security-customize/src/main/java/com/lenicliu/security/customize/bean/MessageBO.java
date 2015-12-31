package com.lenicliu.security.customize.bean;

import com.lenicliu.security.customize.domain.Message;
import com.lenicliu.security.customize.domain.User;

public class MessageBO extends Message {

	private User user;

	public MessageBO(Message message, User user) {
		super(message);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}