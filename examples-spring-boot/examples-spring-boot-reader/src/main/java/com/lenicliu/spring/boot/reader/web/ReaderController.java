package com.lenicliu.spring.boot.reader.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReaderController {

	@RequestMapping("/index")
	public Object index(Principal principal) {
		Map<String, Object> model = new HashMap<>();
		model.put("principal", principal.getName());
		return model;
	}
}