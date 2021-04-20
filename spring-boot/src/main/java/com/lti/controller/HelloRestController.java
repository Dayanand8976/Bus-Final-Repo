package com.lti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
	
	@GetMapping("/hello")
	public @ResponseBody String hello() {
		return "Welcome to Spring Boot";
	}
	
	
}






































