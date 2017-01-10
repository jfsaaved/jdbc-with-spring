package com.jfsaaved.model;

public class Circle {

	private int id;
	private String name;
	
	public Circle () {
		
	}
	
	public Circle(int id, String name) {
		this.id = id;
		this.setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
