package com.lenicliu.jboot.showcase.commons;

import java.util.Objects;

class LinkImpl implements Link {

	private String	name;
	private String	href;
	private String	method;

	LinkImpl(String name, String href, String method) {
		this.name = Objects.requireNonNull(name, "name is requried");
		this.href = Objects.requireNonNull(href, "href is requried");
		this.method = Objects.requireNonNull(method, "href is requried");
	}

	@Override
	public String getHref() {
		return href;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getMethod() {
		return method;
	}
}