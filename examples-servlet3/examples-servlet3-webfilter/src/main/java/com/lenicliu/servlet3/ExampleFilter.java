package com.lenicliu.servlet3;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(//
urlPatterns = { "/*" }, //
filterName = "ExampleFilter", //
displayName = "ExampleFilter", //
initParams = { //
		@WebInitParam(name = "p1", value = "v1"), //
		@WebInitParam(name = "p2", value = "v2")//
})
public class ExampleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init filter");
		System.out.println("p1=" + filterConfig.getInitParameter("p1"));
		System.out.println("p2=" + filterConfig.getInitParameter("p2"));
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("before filter");
		chain.doFilter(request, response);
		System.out.println("after filter");
	}

	@Override
	public void destroy() {
		System.out.println("destroy filter");
	}
}