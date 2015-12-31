package com.lenicliu.security.customize.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.security.customize.domain.Role;
import com.lenicliu.security.customize.web.AdminController;

@Controller
@RequestMapping("/roles")
public class RoleController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		return "role/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		return "role/input";
	}

	@RequestMapping("/submit")
	public String submit(Role role) {
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		return "redirect:";
	}
}