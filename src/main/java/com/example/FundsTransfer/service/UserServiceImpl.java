package com.example.FundsTransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FundsTransfer.model.LoginResponse;
import com.example.FundsTransfer.model.User;
import com.example.FundsTransfer.repository.UserRepository;
import com.example.FundsTransfer.security.JwtService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;

	@Override
	public Optional<User> getUserById(Long id) {
		Optional<User> user=userRepository.findById(id);
		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public Optional<User> getUserByUsername(String username) {
		Optional<User> user=userRepository.findByUsername(username);

		// TODO Auto-generated method stub
		return user;
	}
	@Override
	public LoginResponse login(String username, String password) {

	    Optional<User> user = userRepository.findByUsername(username);
	    if(user.isEmpty())
	    {
	    	throw new RuntimeException("Wrong username or password");
	    }
	    LoginResponse loginResponse=new LoginResponse();
	    if(passwordEncoder.matches(password, user.get().getPassword())){
	    	loginResponse.setUsername(username);
	    	loginResponse.setToken(jwtService.generateToken(username));
	    	return loginResponse;
	    }
	    else
	    {
	    	throw new RuntimeException("Wrong username or password");
	    }

	}
	@Override
	public LoginResponse signUp(User user) {
		// TODO Auto-generated method stub
		if(userRepository.existsByUsername(user.getUsername())) {
	        throw new RuntimeException("Username already exists");
	    }
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	    LoginResponse loginResponse=new LoginResponse();
		loginResponse.setUsername(user.getUsername());
    	loginResponse.setToken(jwtService.generateToken(user.getUsername()));
    	return loginResponse;	}

}
