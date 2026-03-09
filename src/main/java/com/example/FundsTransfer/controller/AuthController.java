package com.example.FundsTransfer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.FundsTransfer.model.LoginResponse;
import com.example.FundsTransfer.model.User;
import com.example.FundsTransfer.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody User request) {
    	return userService.login(request.getUsername(), request.getPassword());
    }
    
    @PostMapping("/signup")
    public LoginResponse signUp(@RequestBody User request) {
    	return userService.signUp(request);
    }
}