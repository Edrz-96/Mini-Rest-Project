package com.esg.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Route {

	@GetMapping("/home")
	public String home() {
		return "hello world";
	}



}
