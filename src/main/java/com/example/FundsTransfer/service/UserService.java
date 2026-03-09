package com.example.FundsTransfer.service;

import java.util.Optional;

import com.example.FundsTransfer.model.LoginResponse;
import com.example.FundsTransfer.model.User;

public interface UserService {
	public Optional<User> getUserById(Long id);
	public Optional<User> getUserByUsername(String username);
	public LoginResponse login(String username, String password);
	public LoginResponse signUp(User user);

}
