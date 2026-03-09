package com.example.FundsTransfer.service;

import java.util.Optional;

import com.example.FundsTransfer.model.Account;
import com.example.FundsTransfer.model.TransferRequest;

public interface AccountService {

	public Optional<Account> getAccountByUserId(Long userId);
	public Optional<Account> getAccountByToken(String token);
//	public Optional<Account> transferFundsByToken(String token, Double amount);
	public void transferFunds(String token,TransferRequest transferRequest);

}
