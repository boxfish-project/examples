package com.lenicliu.jboot.showcase.commons;

public interface Link {

	String getHref();

	String getName();

	public static class Builder {

		public static Link buildSelf(String href) {
			return build("_self", href);
		}

		public static Link build(String name, String href) {
			return new LinkImpl(name, href);
		}
	}
}