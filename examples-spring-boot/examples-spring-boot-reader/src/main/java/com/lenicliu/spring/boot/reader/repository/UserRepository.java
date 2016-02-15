package com.lenicliu.spring.boot.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lenicliu.spring.boot.reader.entity.User;

/**
 * @author lenicliu 2016-02-11
 *
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}