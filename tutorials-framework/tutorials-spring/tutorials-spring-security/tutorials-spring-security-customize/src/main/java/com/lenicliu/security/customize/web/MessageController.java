package com.lenicliu.security.customize.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.security.customize.domain.Message;
import com.lenicliu.security.customize.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageController extends WebController {

	@Autowired
	private MessageService messageService;

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("messages", messageService.findList(keyword));
		return "message/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		model.addAttribute("message", messageService.findById(id));
		return "message/input";
	}

	@RequestMapping("/submit")
	public String submit(Message message) throws Exception {
		message.setUid(getCurrentUserDetails().getUser().getId());
		messageService.createMessage(message);
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		messageService.deleteMessage(id);
		return "redirect:";
	}
}