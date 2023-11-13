package com.jsp.springboot1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.springboot1.dto.Login;
import com.jsp.springboot1.dto.ResponseStructure;
import com.jsp.springboot1.dto.User;
import com.jsp.springboot1.exception.NoSuchUserIdFoundException;
import com.jsp.springboot1.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	//Save
	@PostMapping("/users")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	//Delete
	@DeleteMapping("/users/{id}")
	public boolean deleteUserById(@PathVariable int id) {
		return userService.deleteUserByID(id);
	}
	
	//Update
	@PutMapping("/users/{id}")
	public User updateByID(@PathVariable int id,@RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	
	//Get By Id
	@GetMapping("/users/{id}")
	public ResponseStructure<User> getUserById(@PathVariable int id) throws NoSuchUserIdFoundException {
		return userService.getUserByID(id);
	}
	
	//GetALL
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/users/login")
	public boolean validateUser(@RequestBody Login login) {
		User u= userService.validateUser(login.getEmail(), login.getPassword());
		if(u!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
