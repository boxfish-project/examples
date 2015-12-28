package com.lenicliu.jboot.showcase.user.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.jboot.showcase.user.domain.User;
import com.lenicliu.jboot.showcase.user.mapper.UserMapper;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		if (Objects.isNull(username)) {
			return null;
		}
		return userMapper.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		if (Objects.isNull(id)) {
			return null;
		}
		return userMapper.findById(id);
	}

	@Override
	public List<User> findList(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return userMapper.findList("%" + keyword.trim() + "%");
	}

	@Override
	public void createUser(User user) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(user.getUsername());
		Objects.requireNonNull(user.getPassword());
		user.setUsername(user.getUsername().trim());
		user.setPassword(user.getPassword().trim());
		user.setCreated(System.currentTimeMillis());
		user.setId(null);
		userMapper.insert(user);
	}

	@Override
	public void updateUser(User user) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(user.getPassword());
		Objects.requireNonNull(user.getId());
		userMapper.update(user);
	}

	@Override
	public void deleteUser(Long id) {
		Objects.requireNonNull(id);
		userMapper.delete(id);
	}
}