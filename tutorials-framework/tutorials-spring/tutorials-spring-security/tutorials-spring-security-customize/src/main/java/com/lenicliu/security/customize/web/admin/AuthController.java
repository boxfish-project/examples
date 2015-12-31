package com.lenicliu.security.customize.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.security.customize.web.AdminController;

@Controller
@RequestMapping("/auths")
public class AuthController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		return "auth/list";
	}
}