package com.user.validation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User_Table")

public class User {
	@Id
	@GeneratedValue
	@Column(name = "User_Id")
	private int userId;
	
	private String name;
	
	private int age;
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public User(int userId, String name, int age) {
		super();
		this.userId = userId;
		this.name = name;
		this.age = age;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
