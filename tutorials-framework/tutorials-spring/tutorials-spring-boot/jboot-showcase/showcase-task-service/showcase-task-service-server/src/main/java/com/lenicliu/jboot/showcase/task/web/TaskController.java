package com.lenicliu.jboot.showcase.task.web;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lenicliu.jboot.showcase.task.domain.Task;
import com.lenicliu.jboot.showcase.task.service.TaskService;

@Controller
@RequestMapping(value = "/tasks", produces = { "text/html" })
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping
	public ModelAndView list(String[] states, String keyword) {
		if (states == null) {
			states = new String[] { Task.State.OPENING.name(), Task.State.WORKING.name(), Task.State.COMPLETED.name() };
		}
		return new ModelAndView("task/list", "tasks", taskService.findList(new Long(1), Arrays.asList(states), keyword));
	}

	@RequestMapping("/input")
	public ModelAndView input(Long id) {
		Task task = null;
		if (id != null) {
			task = taskService.find(id);
		}
		if (task == null || task.getId() == null) {
			task = new Task();
		}
		return new ModelAndView("task/input", "task", task);
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		if (id != null) {
			taskService.deleteTask(id);
		}
		return "redirect:";
	}

	@RequestMapping("/submit")
	public String submit(Task task) {
		task = Objects.requireNonNull(task);
		if (task.getId() != null) {
			taskService.updateTask(task);
			return "redirect:input?id=" + task.getId();
		} else {
			taskService.createTask(task);
			return "redirect:";
		}
	}
}