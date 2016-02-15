package com.lenicliu.spring.boot.reader.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lenicliu.spring.boot.reader.entity.User;
import com.lenicliu.spring.boot.reader.repository.UserRepository;

@Service
public class WebSecurityService implements UserDetailsService, InitializingBean {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new WebSecurityDetails(user.getUsername(), user.getPassword());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		User user = userRepository.findByUsername("admin");
		if (user == null) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("e10adc3949ba59abbe56e057f20f883e");
			userRepository.save(user);
		}
	}
}