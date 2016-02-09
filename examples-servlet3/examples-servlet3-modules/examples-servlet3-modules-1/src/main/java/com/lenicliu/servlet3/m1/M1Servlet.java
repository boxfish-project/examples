package com.lenicliu.servlet3.m1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class M1Servlet extends HttpServlet {

	private static final long serialVersionUID = 5421577640933428425L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("m1.jsp").forward(req, resp);
	}
}