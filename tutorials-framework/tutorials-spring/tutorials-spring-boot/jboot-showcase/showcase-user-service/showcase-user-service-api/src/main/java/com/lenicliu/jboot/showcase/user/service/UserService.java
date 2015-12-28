package com.lenicliu.jboot.showcase.user.service;

import java.util.List;

import com.lenicliu.jboot.showcase.user.domain.User;

public interface UserService {

	public User findByUsername(String username);

	public User findById(Long id);

	public List<User> findList(String keyword);

	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(Long id);
}