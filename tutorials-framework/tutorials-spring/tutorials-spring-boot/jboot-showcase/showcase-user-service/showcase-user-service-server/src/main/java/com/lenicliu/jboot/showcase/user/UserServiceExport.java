package com.lenicliu.jboot.showcase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.lenicliu.jboot.showcase.user.service.UserService;

@Configuration
public class UserServiceExport {

	@Autowired
	private UserService userService;

	@Bean(name = "/remote/UserService")
	public HttpInvokerServiceExporter userService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(UserService.class);
		exporter.setService(userService);
		return exporter;
	}
}