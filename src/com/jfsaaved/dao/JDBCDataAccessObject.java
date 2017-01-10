package com.jfsaaved.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.stereotype.Component;
import com.jfsaaved.model.Circle;

@Component
public class JDBCDataAccessObject {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private NamedParameterJdbcTemplate simpleJdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		//this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getCircleName(int id) {
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return (String) jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
	}
	
	public Circle getCircleFromID(int id) {
		String sql = "SELECT * FROM CIRCLE WHERE ID = ?";
		return (Circle) jdbcTemplate.queryForObject(sql, new Object[] {id}, new CircleMapper());
	}
	
	public List<Circle> getAllCircles() {
		String sql = "SELECT * FROM CIRCLE";
		@SuppressWarnings("unchecked")
		List<Circle> list = jdbcTemplate.query(sql, new CircleMapper());
		return list;
	}
	
//	public void insertCircle(Circle circle) {
//		String sql = "INSERT INTO CIRCLE (ID, NAME) VALUES (?, ?)";
//		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
//	}
	
	public void insertCircle(Circle circle) {
		String sql = "INSERT INTO CIRCLE (ID, NAME) VALUES (:id, :name)";
		SqlParameterSource nameParameters = new MapSqlParameterSource("id", circle.getId())
				.addValue("name", circle.getName());
		namedParameterJdbcTemplate.update(sql, nameParameters);
	}
	
	public void createTriangleTable() {
		String sql = "CREATE TABLE TRIANGLE (ID INTEGER, NAME VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	
	
	private static final class CircleMapper implements RowMapper {
		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(rs.getInt("ID"));
			circle.setName(rs.getString("NAME"));
			return circle;
		}
	}

}
