package com.lenicliu.jboot.showcase.commons;

import java.util.List;

public class ResourceCollection<T> extends ResourceElement<List<T>> {

	public ResourceCollection() {
		super();
	}

	public ResourceCollection(List<T> value, Link... links) {
		super(value, links);
	}

	public ResourceCollection(List<T> value, List<Link> links) {
		super(value, links);
	}

	public ResourceCollection(List<T> value) {
		super(value);
	}
}