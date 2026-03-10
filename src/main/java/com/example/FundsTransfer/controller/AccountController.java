package com.example.FundsTransfer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FundsTransfer.model.Account;
import com.example.FundsTransfer.model.TransferRequest;
import com.example.FundsTransfer.model.TransferResponse;
import com.example.FundsTransfer.service.AccountService;


@RestController
@RequestMapping("/api")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/get-account")
	public Optional<Account> getAccount(@RequestHeader("Authorization") String authHeader) {
		return accountService.getAccountByToken(authHeader);
	     
	}
	
	@PostMapping("/transfer")
	public TransferResponse transferFunds(@RequestHeader("Authorization") String authHeader,@RequestBody() TransferRequest transferRequest) {
		return accountService.transferFunds(authHeader,transferRequest);
	     
	}

}
