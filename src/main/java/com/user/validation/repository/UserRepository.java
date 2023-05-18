package com.user.validation.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.validation.dto.UserRequest;
import com.user.validation.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);

	void save(@Valid UserRequest userRequest);

}
