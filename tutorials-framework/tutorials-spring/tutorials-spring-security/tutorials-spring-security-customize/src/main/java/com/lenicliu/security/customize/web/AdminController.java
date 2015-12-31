package com.lenicliu.security.customize.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.lenicliu.security.customize.service.AdminService;

public abstract class AdminController extends WebController {

	@Autowired
	protected AdminService adminService;
}