package com.user.validation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




public class UserRequest {
	
	@NotEmpty(message="Name cannot be empty or null,name Must be of 2 - 30 characters")
    @Size(min = 2, max = 30,message="Name cannot be empty or null,name Must be of 2 - 30 characters")
	private String name;
	

	@Max(value = 70,message ="Age can't be null or empty, must between 18 to 70")
	@Min(value = 18,message = "Age can't be null or empty, must between 18 to 70")
	private int age;

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

	
}
