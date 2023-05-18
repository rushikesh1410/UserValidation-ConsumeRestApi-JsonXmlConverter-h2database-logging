package com.user.validation.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.validation.entity.Loggs;

public interface LoggsRepo extends JpaRepository<Loggs, Integer> {

	
	void save(LocalDateTime date);

	void save(String operation);

}
