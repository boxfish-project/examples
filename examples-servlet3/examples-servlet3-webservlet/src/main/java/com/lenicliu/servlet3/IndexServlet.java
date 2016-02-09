package com.lenicliu.servlet3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/**")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = -6087859606279926562L;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, res);
	}
}