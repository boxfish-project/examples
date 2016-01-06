package com.lenicliu.jboot.showcase.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.lenicliu.jboot.showcase.task.service.TaskService;

@Configuration
public class TaskServiceExport {

	@Autowired
	private TaskService taskService;

	@Bean(name = "/remote/TaskService")
	public HttpInvokerServiceExporter userService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(TaskService.class);
		exporter.setService(taskService);
		return exporter;
	}
}