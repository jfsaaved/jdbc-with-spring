package com.jfsaaved.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jfsaaved.dao.HibernateDaoImpl;
import com.jfsaaved.dao.JDBCDataAccessObject;
import com.jfsaaved.dao.SimpleJdbcDaoImpl;
import com.jfsaaved.model.Circle;

public class Main {
	
	private static ApplicationContext ctx;

	public static void main(String[] args) {
		
		ctx = new ClassPathXmlApplicationContext("spring.xml");
		//JDBCDataAccessObject jdbcdao = ctx.getBean("JDBCDataAccessObject", JDBCDataAccessObject.class);
		//SimpleJdbcDaoImpl jdbcdao = ctx.getBean("simpleJdbcDaoImpl", SimpleJdbcDaoImpl.class);
		HibernateDaoImpl jdbcdao = ctx.getBean("hibernateDaoImpl", HibernateDaoImpl.class);
		System.out.println(jdbcdao.getCircleCount());
		
		//Circle circle = jdbcdao.getCircle(1);
		//System.out.println(circle.getName());
		
		//System.out.println(jdbcdao.getCircleName(1));
		//System.out.println(jdbcdao.getCircleFromID(1).getId() + ", " + jdbcdao.getCircleFromID(1).getName());
		
		//jdbcdao.insertCircle(new Circle(5,"Fifth Circle"));
		//System.out.println(jdbcdao.getAllCircles().size());
		
		//jdbcdao.createTriangleTable();
		
	}

}
