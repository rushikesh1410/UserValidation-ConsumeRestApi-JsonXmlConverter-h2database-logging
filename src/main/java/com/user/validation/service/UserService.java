package com.user.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.validation.dto.UserRequest;
import com.user.validation.entity.User;
import com.user.validation.exception.UserNotFoundException;
import com.user.validation.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	
//save 1 user
	public User saveUser(UserRequest userRequest) {
		User user = new User(0, userRequest.getName(), userRequest.getAge());
		return userRepo.save(user);
	}

	
	
// list of all the users
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	
	
	
// user not found 404
	public User getUser(int id) throws UserNotFoundException {
		User user =  userRepo.findByUserId(id);
		if(user!= null) 
		{
			return user;
		}
		else 
		{
			throw new UserNotFoundException("User not found with Id: "+id);
		}
	}

	

}
