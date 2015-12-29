package com.lenicliu.jboot.showcase.task.service;

import java.util.List;

import com.lenicliu.jboot.showcase.task.domain.Task;

public interface TaskService {

	Task find(Long id);

	List<Task> findList(Long uid, List<String> states, String keyword);

	void createTask(Task task);

	void deleteTask(Long id);

	void updateTask(Task task);
}