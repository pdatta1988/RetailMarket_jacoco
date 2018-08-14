package com.hackerrank.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TestController {
	
	@RequestMapping(value="/name", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getX() {
		System.out.println("My name..");
		return new ResponseEntity<String>("okay boss", HttpStatus.OK);
	}

}
