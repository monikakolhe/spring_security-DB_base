package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping( value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return new ResponseEntity<>(this.userService.addUser(user),HttpStatus.OK);
		}
	@RequestMapping( value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<?> showAllUser() {
		return new ResponseEntity<>(this.userService.getAll(),HttpStatus.OK);
		}
}
