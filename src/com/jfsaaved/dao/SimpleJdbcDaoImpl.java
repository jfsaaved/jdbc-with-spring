package com.jfsaaved.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleJdbcDaoImpl extends NamedParameterJdbcTemplate{
	
	public SimpleJdbcDaoImpl(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	public int getCircleCount() {
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		return this.getJdbcOperations().queryForObject(sql, Integer.class);
	}

}
