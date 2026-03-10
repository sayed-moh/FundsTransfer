package com.example.FundsTransfer.service;

import java.util.Optional;

import com.example.FundsTransfer.model.Account;
import com.example.FundsTransfer.model.TransferRequest;
import com.example.FundsTransfer.model.TransferResponse;

public interface AccountService {

	public Optional<Account> getAccountByUserId(Long userId);
	public Optional<Account> getAccountByToken(String token);
	public TransferResponse transferFunds(String token,TransferRequest transferRequest);

}
