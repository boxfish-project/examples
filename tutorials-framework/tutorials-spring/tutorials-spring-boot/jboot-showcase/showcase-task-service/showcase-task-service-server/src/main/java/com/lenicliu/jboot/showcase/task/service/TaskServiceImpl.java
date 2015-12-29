package com.lenicliu.jboot.showcase.task.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.jboot.showcase.task.domain.Task;
import com.lenicliu.jboot.showcase.task.repository.TaskRepository;
import com.lenicliu.jboot.showcase.task.service.TaskService;

@Transactional
@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task find(Long id) {
		if (id == null) {
			return null;
		}
		return taskRepository.findById(id);
	}

	@Override
	public List<Task> findList(Long uid, List<String> states, String keyword) {
		uid = new Long(1);
		Objects.requireNonNull(uid);
		return taskRepository.findList(uid, states, keyword);
	}

	@Override
	public void createTask(Task task) {
		Objects.requireNonNull(task);
		task.setId(null);
		task.setUid(new Long(1));
		task.setState(Task.State.OPENING.name());
		taskRepository.insert(task);
	}

	@Override
	public void deleteTask(Long id) {
		if (id != null) {
			taskRepository.delete(id);
		}
	}

	@Override
	public void updateTask(Task task) {
		Objects.requireNonNull(task);
		Objects.requireNonNull(task.getId());
		taskRepository.update(task);
	}
}