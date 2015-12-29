package com.lenicliu.jboot.showcase.user.endpoint;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
import com.lenicliu.jboot.showcase.user.domain.User;
import com.lenicliu.jboot.showcase.user.service.UserService;

/**
 * <pre>
 * GET	/api/users/xxx	search
 * GET	/api/users	search
 * POST	/api/users	create
 * PUT	/api/users	modify
 * DELETE	/api/users/1	delete
 * </pre>
 * 
 * @author lenicliu
 *
 */
@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserEndpoint {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public Resource<User> get(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if (user == null) {
			// user not found
			return new ResourceElement<User>();
		}
		Link _self = Link.Builder.buildSelf("/api/users/" + username, HttpMethod.GET.name());
		Link _modify = Link.Builder.build("_modify", "/api/users/" + username, HttpMethod.PUT.name());
		Link _delete = Link.Builder.build("_delete", "/api/users/" + username, HttpMethod.DELETE.name());
		return new ResourceElement<User>(user, _self, _modify, _delete);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Resource<List<User>> getList(String keyword) {
		List<User> users = userService.findList(keyword);
		if (users == null) {
			users = Collections.emptyList();
		}
		return new ResourceCollection<User>(users, Link.Builder.buildSelf("/api/users", HttpMethod.GET.name()));
	}

	@RequestMapping(method = RequestMethod.POST)
	public void post(String username, String password, String password2) {
		if (Objects.equals(password, password2)) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userService.createUser(user);
		}
		// diff password
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		User existing = userService.findById(id);
		if (existing != null) {
			userService.deleteUser(id);
		}
		// user not found
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void put(Long id, String password, String _password, String _password2) {
		if (Objects.equals(_password, _password2)) {
			User existing = userService.findById(id);
			if (existing != null) {
				if (Objects.nonNull(existing.getPassword()) && Objects.equals(password, existing.getPassword())) {
					User user = new User();
					user.setId(id);
					user.setPassword(_password);
					userService.updateUser(user);
				}
				// wrong password
			}
			// user not found
		}
		// diff password
	}
}