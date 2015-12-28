package com.lenicliu.jboot.showcase.user.web;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lenicliu.jboot.showcase.user.domain.User;
import com.lenicliu.jboot.showcase.user.service.UserService;

@Controller
@RequestMapping(value = "/users", produces = { "text/html" })
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping
	public ModelAndView list(String keyword) {
		List<User> users = userService.findList(keyword);
		return new ModelAndView("user/list", "users", users);
	}

	@RequestMapping("/input")
	public ModelAndView input(Long id) {
		User user = null;
		if (id != null) {
			user = userService.findById(id);
		}
		if (user == null || user.getId() == null) {
			user = new User();
		}
		return new ModelAndView("user/input", "user", user);
	}

	@RequestMapping("/submit")
	public String submit(User user) {
		user = Objects.requireNonNull(user);
		if (user.getId() != null) {
			userService.updateUser(user);
			return "redirect:users/input?id=" + user.getId();
		} else {
			userService.createUser(user);
			return "redirect:users";
		}
	}
}