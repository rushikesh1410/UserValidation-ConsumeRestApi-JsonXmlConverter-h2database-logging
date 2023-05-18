package com.user.validation.entity;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Errors {

	private Map<String,String> errorMessage;


	public Map<String, String> getErrorMessage() {
		return errorMessage;
	}
	
	
	public void setErrorMessage(Map<String, String> errorMessage) {
		this.errorMessage = errorMessage;
	}



	public void setError(Map<String, String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Errors(Map<String, String> errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public Errors() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
