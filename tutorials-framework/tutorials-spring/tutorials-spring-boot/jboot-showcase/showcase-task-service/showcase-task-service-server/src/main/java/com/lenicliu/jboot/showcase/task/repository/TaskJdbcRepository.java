package com.lenicliu.jboot.showcase.task.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lenicliu.jboot.showcase.task.domain.Task;

@Repository
public class TaskJdbcRepository implements TaskRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Task findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM TB_TASK WHERE ID = ?", new BeanPropertyRowMapper<Task>(Task.class), id);
	}

	@Override
	public void update(Task task) {
		jdbcTemplate.update("UPDATE TB_TASK SET TITLE = ?, DUE = ? WHERE ID = ?", task.getTitle(), task.getDue(), task.getId());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM TB_TASK WHERE ID = ?", id);
	}

	@Override
	public void insert(Task task) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("TB_TASK");
		insert.usingGeneratedKeyColumns("ID");
		insert.execute(new BeanPropertySqlParameterSource(task));
	}

	@Override
	public List<Task> findList(Long uid, List<String> states, String keyword) {
		StringBuffer sql = new StringBuffer();
		List<Object> args = new ArrayList<>();
		sql.append("SELECT * FROM TB_TASK WHERE");
		if (keyword == null) {
			keyword = "";
		}
		sql.append(" TITLE LIKE ? ");
		args.add("%" + keyword.trim() + "%");
		sql.append(" AND UID = ? ");
		args.add(uid);
		if (states != null && !states.isEmpty()) {
			StringBuffer in = new StringBuffer();
			for (String state : states) {
				in.append(",?");
				args.add(state);
			}
			sql.append(" AND STATE IN (" + in.substring(1) + ")");
		}
		return jdbcTemplate.query(sql.toString(), args.toArray(new Object[args.size()]), new BeanPropertyRowMapper<Task>(Task.class));
	}
}