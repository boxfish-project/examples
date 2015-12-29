package com.lenicliu.jboot.showcase.commons;

public interface Link {

	String getHref();

	String getName();

	String getMethod();

	public static class Builder {

		public static Link buildSelf(String href, String method) {
			return build("_self", href, method);
		}

		public static Link build(String name, String href, String method) {
			return new LinkImpl(name, href, method);
		}
	}
}