package com.example.FundsTransfer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FundsTransfer.model.User;
import com.example.FundsTransfer.repository.UserRepository;
import com.example.FundsTransfer.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/api/create")
	public void createUser(@RequestBody User user) {
	     userRepository.save(user);
	}
	@GetMapping(value = "/api/get")
	public Optional<User> getUserById(@RequestParam Long id)
	{
		return userService.getUserById(id);
	}


}
