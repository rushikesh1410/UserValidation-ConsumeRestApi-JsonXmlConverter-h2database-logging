package com.user.validation.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.validation.entity.Errors;
import com.user.validation.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

	@Autowired
	private Errors errorsClass;

	
//validation and message not readable 400 bad request
	
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Errors> handleInvalidArgument(Exception ex, HttpServletRequest req) {
		HttpHeaders headers = new HttpHeaders();

		Map<String, String> errors = new HashMap<>();

		if (req.getHeader("Content-Type").contains("application/Json")) {
			if (req.getHeader("Accept").contains("application/json")) {
				headers.set("Content-type", "application/json");
			}
		}
		if (req.getHeader("Content-Type").contains("application/Json")) {
			if (req.getHeader("Accept").contains("application/xml")) {
				headers.set("Content-type", "application/xml");
			}
		}
		if (req.getHeader("Content-Type").contains("application/xml")) {
			if (req.getHeader("Accept").contains("application/json")) {
				headers.set("Content-type", "application/json");
			}
		}
		if (req.getHeader("Content-Type").contains("application/xml")) {
			if (req.getHeader("Accept").contains("application/xml")) {
				headers.set("Content-type", "application/xml");
			}
		}

		if (!(req.getHeader("id").contains("12345"))) {
			Map<String, String> maps = new HashMap<>();
			maps.put("invalidId", "id entered is wrong");
			errorsClass.setError(maps);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(errorsClass);
		}

		if (req.getHeader("Content-Type").equals("application/json")) {

			String error = ex.getMessage();

			if (error.contains("int")) {
				error = "Age must be a number";
				errors.put("invalidAge", error);
			}

			if (error.contains("Unexpected character ('}' (code 125)): expected a value;")) {
				error = "Age should not be empty";
				errors.put("invalidAge", error);
			}

			if (error.contains("Unexpected character (',' (code 44)): expected a value;")) {
				error = "Name should not be empty";
				errors.put("invalidName", error);
			}

			if (error.contains(
					" Unexpected character ('\"' (code 34)): was expecting comma to separate Object entries")) {
				error = "comma expected to separate object entries";
				errors.put("syntaxError", error);
			}

			if (error.contains("Unexpected end-of-input: expected close marker for Object")) {
				error = "expected close marker for Object";
				errors.put("syntaxError", error);
			}

			if (error.contains("Cannot construct instance of `com.user.validation.dto.UserRequest`")) {
				error = "Invalid Json or expected start marker for Object";
				errors.put("syntaxError", error);
			}

			if (error.contains(
					" was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false'")) {
				error = "check the field 'age', age must be a number ";
				errors.put("invalidAge", error);
			}

			if (error.contains("Unexpected character (',' (code 44)): expected a value")) {
				error = "Name cannot be empty or null,name Must be of 2 - 30 characters";
				errors.put("invalidName", error);
			}

			if (error.contains("was expecting a colon to separate field name and value")) {
				error = "a colon is expected to separate the field name and the value";
				errors.put("syntaxError", error);
			}

			if (error.contains("expected a value")) {
				error = "Age can't be null or empty, must between 18 to 70";
				errors.put("invalidAge", error);
			}

		}

		if (req.getHeader("Content-Type").equals("application/xml")) {
			String error = ex.getMessage();

			if (error.contains("int")) {
				error = "Age must be a number";
				errors.put("invalidAge", error);
			}

			if (error.contains("end tag Expected '>'")) {
				error = "end tag Expected '>'";
				errors.put("syntaxError", error);
			}

			if (error.contains("(missing closing '>'?)")) {
				error = "end tag Expected '>'";
				errors.put("syntaxError", error);
			}

			if (error.contains("Unexpected end of input block in end tag")) {
				error = "end tag Expected '>'";
				errors.put("syntaxError", error);
			}

			if (error.contains("[1,1]")) {
				error = "Start tag Expected '<' ";
				errors.put("syntaxError", error);
			}

			if (error.contains("Validation failed for object")) {
				error = "<User></User> is expected";
				errors.put("invalidXml", error);
			}

			if (error.contains("was expecting a close tag for element")) {
				error = "tag is expected for element < User>";
				errors.put("syntaxError", error);
			}

			if (error.contains("Unexpected close tag")) {
				error = "tag is expected ' <'";
				errors.put("syntaxError", error);
			}

			if (error.contains("Required request body")) {
				error = "Required request body is missing";
				errors.put("errorMessage", error);
			}
		}

		errorsClass.setError(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errorsClass);
	}

	
	
	
	
// user not found 404 method 1

//	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public Map<String, String> handleInvalidIdException(UserNotFoundException ex)
//	{
//
//		Map<String, String> maps = new HashMap<>();
//		maps.put("errorMessage", ex.getMessage());
//		return maps;
//	}

// user not found 404 method 2

	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleInvalidException(UserNotFoundException ex) {

		Map<String, String> maps = new HashMap<>();

		maps.put("userNotFound", ex.getMessage());

		errorsClass.setError(maps);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsClass);
	}
	
	
	
	

// INTERNAL_SERVER_ERROR 500

	@org.springframework.web.bind.annotation.ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleinternalServerError(CannotCreateTransactionException ex,
			HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = ex.getMessage();

		if (errorMessage.contains("Could not open JPA EntityManager for transaction")) {
			errorMessage = "Cannot connect to DataBase Server";
			errors.put("dataBaseError", errorMessage);
		}
		errorsClass.setError(errors);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorsClass);
	}
	
	
	

	// UNAUTHORIZED 401

	@org.springframework.web.bind.annotation.ExceptionHandler(java.lang.NullPointerException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<?> handleUnauthorizedError(java.lang.NullPointerException ex, HttpServletRequest req) {
		Map<String, String> errors = new HashMap<>();

		HttpHeaders headers = new HttpHeaders();

		String error = ex.getMessage();

		if (error.contains(
				"Cannot invoke \"String.contains(java.lang.CharSequence)\" because the return value of \"javax.servlet.http.HttpServletRequest.getHeader(String)\" is null")) {
			error = "enter id";
			errors.put("invalidId", error);
		}

		errorsClass.setError(errors);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(errorsClass);
	}

}
