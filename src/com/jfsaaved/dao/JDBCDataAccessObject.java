package com.jfsaaved.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jfsaaved.model.Circle;

@Component
public class JDBCDataAccessObject {
	
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public Circle getCircle(int id) {
		Connection conn = null;
		
		try{

			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
			ps.setInt(1, id);
			
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				circle = new Circle(id, rs.getString("name"));
			}
			
			rs.close();
			ps.close();
			
		return circle;
		} catch (Exception e) {
			System.out.println("ERROR");
		} finally {
			try {
				conn.close();
			} catch(SQLException se) {
				System.out.println("ERROR");
			}
		}
		
		return null;

	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
		return jdbcTemplate.queryForInt(sql);
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
