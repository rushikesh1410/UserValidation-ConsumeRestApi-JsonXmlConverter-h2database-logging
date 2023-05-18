package com.user.validation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.validation.dto.UserRequest;
import com.user.validation.entity.Errors;
import com.user.validation.entity.User;
import com.user.validation.exception.UserNotFoundException;
import com.user.validation.repository.UserRepository;
import com.user.validation.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private Errors errorClass;

	@PostMapping(value = "/signup", consumes = MediaType.ALL_VALUE, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest, BindingResult result,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

		HttpHeaders headers = new HttpHeaders();

		if (request.getHeader("Content-Type").contains("application/Json")) {
			if (request.getHeader("Accept").contains("application/json")) {
				headers.set("Content-type", "application/json");
			}
		}
		if (request.getHeader("Content-Type").contains("application/Json")) {
			if (request.getHeader("Accept").contains("application/xml")) {
				headers.set("Content-type", "application/xml");
			}
		}
		if (request.getHeader("Content-Type").contains("application/xml")) {
			if (request.getHeader("Accept").contains("application/json")) {
				headers.set("Content-type", "application/json");
			}
		}
		if (request.getHeader("Content-Type").contains("application/xml")) {
			if (request.getHeader("Accept").contains("application/xml")) {
				headers.set("Content-type", "application/xml");
			}
		}

		if (!(request.getHeader("id").contains("12345"))) {
			Map<String, String> maps = new HashMap<>();
			maps.put("invalidId", "Id entered is wrong");
			errorClass.setError(maps);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(errorClass);
		}

		if (result.hasErrors()) {
			Map<String, String> maps = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				maps.put("error" + error.getField().substring(0, 1).toUpperCase() + error.getField().substring(1),
						error.getDefaultMessage());
				errorClass.setError(maps);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errorClass);
		}

		return new ResponseEntity<>(service.saveUser(userRequest), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<?> getAllUser(javax.servlet.http.HttpServletRequest request) {
		
		HttpHeaders headers = new HttpHeaders();
		if (!(request.getHeader("id").contains("12345"))) {
			Map<String, String> maps = new HashMap<>();
			maps.put("invalidId", "Id entered is wrong");
			errorClass.setError(maps);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(errorClass);
		}
		return ResponseEntity.ok(service.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> GetUserById(@PathVariable int id,javax.servlet.http.HttpServletRequest request) throws UserNotFoundException {
	
		HttpHeaders headers = new HttpHeaders();
		if (!(request.getHeader("id").contains("12345"))) {
			Map<String, String> maps = new HashMap<>();
			maps.put("invalidId", "Id entered is wrong");
			errorClass.setError(maps);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(errorClass);
		}
		return ResponseEntity.ok(service.getUser(id));
	}

}
