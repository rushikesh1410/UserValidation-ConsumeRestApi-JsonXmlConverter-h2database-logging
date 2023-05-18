package com.user.validation.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.user.validation.repository.*;
import com.user.validation.entity.*;


@RestController
public class jsonController {

	@Autowired
	LoggsRepo loggsRepo;
	
	Logger LOGGER = LoggerFactory.getLogger(jsonController.class);

	@PostMapping(value = "/call", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
	public String xmljson(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (request.getHeader("Content-Type").equals("application/json")) {
			if (request.getHeader("Accept").equals("application/xml")) {
			
				String operation = "json to xml";
				LocalDateTime date = LocalDateTime.now();
				Loggs loggs = new Loggs(date, operation);
				loggsRepo.save(loggs);
				
				LOGGER.info("operation : "+operation);
				
				JSONObject json = new JSONObject(string);
				return XML.toString(json);
			}

			String operation = "json to json";
			LocalDateTime date = LocalDateTime.now();
			Loggs loggs = new Loggs(date, operation);
			loggsRepo.save(loggs);
			return string;
		}

		if (request.getHeader("Content-Type").equals("application/xml")) {
			
			if (request.getHeader("Accept").equals("application/json")) {
				
				String operation = "xml to json";
				LocalDateTime date = LocalDateTime.now();
				Loggs loggs = new Loggs(date, operation);
				loggsRepo.save(loggs);

				JSONObject json = XML.toJSONObject(string);
				return json.toString();
			}

			String operation = "xml to xml";
			LocalDateTime date = LocalDateTime.now();
			Loggs loggs = new Loggs(date, operation);
			loggsRepo.save(loggs);
			return string;
		}
		return string;
	}
}
