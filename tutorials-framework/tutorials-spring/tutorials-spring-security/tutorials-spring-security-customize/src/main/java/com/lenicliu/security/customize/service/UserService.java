package com.lenicliu.security.customize.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lenicliu.security.customize.domain.User;

public interface UserService extends UserDetailsService {

	User findById(Long id);

}