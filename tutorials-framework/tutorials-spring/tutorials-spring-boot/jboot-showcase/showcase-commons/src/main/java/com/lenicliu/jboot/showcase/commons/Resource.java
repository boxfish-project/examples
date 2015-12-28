package com.lenicliu.jboot.showcase.commons;

import java.util.List;

public interface Resource<T> {

	T getValue();

	List<Link> getLinks();
}