package com.lenicliu.jboot.showcase.task.endpoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lenicliu.jboot.showcase.commons.Link;
import com.lenicliu.jboot.showcase.commons.Resource;
import com.lenicliu.jboot.showcase.commons.ResourceCollection;
import com.lenicliu.jboot.showcase.commons.ResourceElement;
import com.lenicliu.jboot.showcase.task.domain.Task;
import com.lenicliu.jboot.showcase.task.service.TaskService;

/**
 * <pre>
 * GET	/api/tasks/xxx	search
 * GET	/api/tasks	search
 * POST	/api/tasks	create
 * PUT	/api/tasks	modify
 * DELETE	/api/tasks/1	delete
 * </pre>
 * 
 * @author lenicliu
 *
 */
@RestController
@RequestMapping(value = "/api/tasks", produces = "application/json")
public class TaskEndpoint {

	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Resource<Task> get(@PathVariable Long id) {
		Task task = taskService.find(id);
		if (task == null) {
			// user not found
			return new ResourceElement<Task>();
		}
		Link _self = Link.Builder.buildSelf("/api/tasks/" + id, HttpMethod.GET.name());
		Link _modify = Link.Builder.build("_modify", "/api/tasks/" + id, HttpMethod.PUT.name());
		Link _delete = Link.Builder.build("_delete", "/api/tasks/" + id, HttpMethod.DELETE.name());
		return new ResourceElement<Task>(task, _self, _modify, _delete);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Resource<List<Task>> getList(String[] states, String keyword) {
		// TODO: get user id from context
		if (states == null) {
			states = new String[] { Task.State.OPENING.name(), Task.State.WORKING.name(), Task.State.COMPLETED.name() };
		}
		List<Task> tasks = taskService.findList(1L, Arrays.asList(states), keyword);
		if (tasks == null) {
			tasks = Collections.emptyList();
		}
		return new ResourceCollection<Task>(tasks, Link.Builder.buildSelf("/api/tasks", HttpMethod.GET.name()));
	}

	@RequestMapping(method = RequestMethod.POST)
	public void post(String title, String due) {
		Task task = new Task();
		task.setDue(due);
		task.setTitle(title);
		taskService.createTask(task);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		Task task = taskService.find(id);
		if (task != null) {
			taskService.deleteTask(id);
		}
		// task not found
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void put(Long id, String title, String due) {
		Task task = taskService.find(id);
		if (task != null) {
			task.setTitle(title);
			task.setDue(due);
			taskService.updateTask(task);
		}
		// task not found
	}
}