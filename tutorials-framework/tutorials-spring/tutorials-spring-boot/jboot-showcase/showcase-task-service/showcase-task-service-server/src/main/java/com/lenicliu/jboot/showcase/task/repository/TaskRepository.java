package com.lenicliu.jboot.showcase.task.repository;

import java.util.List;

import com.lenicliu.jboot.showcase.task.domain.Task;

public interface TaskRepository {

	public Task findById(Long id);

	public void update(Task task);

	public void delete(Long id);

	public void insert(Task task);

	public List<Task> findList(Long uid, List<String> states, String keyword);
}