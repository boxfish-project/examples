package com.lenicliu.security.customize.web.admin;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.security.customize.domain.User;
import com.lenicliu.security.customize.web.AdminController;

@Controller
@RequestMapping("/admin/users")
public class UserController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("users", adminService.findUserList(keyword));
		model.addAttribute("keyword", keyword);
		return "user/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		model.addAttribute("user", adminService.findUserById(id));
		model.addAttribute("role_ids", adminService.findRoleIds(id));
		model.addAttribute("roles", adminService.findAllRoles());
		return "user/input";
	}
	
	@RequestMapping("/view")
	public String view(Model model, Long id) {
		model.addAttribute("readonly", "readonly");
		model.addAttribute("disabled", "disabled");
		return input(model, id);
	}

	@RequestMapping("/submit")
	public String submit(User user, Long[] role_ids) {
		if (role_ids == null) {
			role_ids = new Long[0];
		}
		if (user.getId() != null) {
			adminService.updateUser(user, Arrays.asList(role_ids));
		} else {
			adminService.createUser(user, Arrays.asList(role_ids));
		}
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		adminService.deleteUser(id);
		return "redirect:";
	}
}