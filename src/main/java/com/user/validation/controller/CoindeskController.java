package com.user.validation.controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CoindeskController {
	
//get mapping task 1
	
	@GetMapping("/coindesk")
	private String getCoin() throws IOException {
		String uri = "https://api.coindesk.com/v1/bpi/currentprice.json";
		RestTemplate restTemplate =new RestTemplate();
		String coins = restTemplate.getForObject(uri, String.class);
		return coins;
	}
	

//headertask 2
		
	@GetMapping("/coinwithheader")
	private String getCoins() throws IOException {
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-IBM-Client-Id", "f7c6f8cc5a0c982a915c9b1b68590c7a");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> Response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return Response.getBody();
	}
}

