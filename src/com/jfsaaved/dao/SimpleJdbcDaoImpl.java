package com.jfsaaved.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport{
	
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
