package com.lenicliu.jboot.showcase.commons;

import java.util.Arrays;
import java.util.List;

public class ResourceElement<T> implements Resource<T> {

	public ResourceElement(T value, List<Link> links) {
		super();
		this.value = value;
		this.links = links;
	}

	public ResourceElement(T value, Link... links) {
		this(value, Arrays.asList(links));
	}

	public ResourceElement(T value) {
		this(value, (List<Link>) null);
	}

	public ResourceElement() {
		this(null, (List<Link>) null);
	}

	private T			value;
	private List<Link>	links;

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public List<Link> getLinks() {
		return links;
	}
}