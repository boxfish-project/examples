package com.lenicliu.jboot.showcase.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lenicliu.jboot.showcase.user.domain.User;

public interface UserMapper {

	@Select("SELECT * FROM TB_USER WHERE USERNAME = #{username}")
	public User findByUsername(@Param("username") String username);

	@Select("SELECT * FROM TB_USER WHERE ID = #{id}")
	public User findById(@Param("id") Long id);

	@Update("UPDATE TB_USER SET PASSWORD = #{user.password} WHERE ID = #{user.id}")
	public void update(@Param("user") User user);

	@Update("DELETE FROM TB_USER WHERE ID = #{id}")
	public void delete(@Param("id") Long id);

	@Insert("INSERT INTO TB_USER(USERNAME, PASSWORD, CREATED)VALUES(#{user.username}, #{user.password}, #{user.created})")
	public void insert(@Param("user") User user);

	@Select("SELECT * FROM TB_USER WHERE USERNAME LIKE #{keyword} ORDER BY CREATED DESC")
	public List<User> findList(@Param("keyword") String keyword);
}