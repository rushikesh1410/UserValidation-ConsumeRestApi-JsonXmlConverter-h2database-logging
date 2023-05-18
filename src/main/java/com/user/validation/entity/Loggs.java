package com.user.validation.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "Loggs_Table")
public class Loggs implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String unique_Id;
	LocalDateTime date;
	String operation;
	
	public String getUnique_Id() {
		return unique_Id;
	}

	public void setUnique_Id(String unique_Id) {
		this.unique_Id = unique_Id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Loggs(LocalDateTime date, String operation) {
		super();
		this.date = date;
		this.operation = operation;
	}
	
}
