package com.example.FundsTransfer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FundsTransfer.model.Account;
import com.example.FundsTransfer.model.TransferRequest;
import com.example.FundsTransfer.model.User;
import com.example.FundsTransfer.repository.AccountRepository;
import com.example.FundsTransfer.security.JwtService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;

	@Override
	public Optional<Account> getAccountByUserId(Long userId) {
		Optional<Account> account=accountRepository.findByUserId(userId);
		// TODO Auto-generated method stub
		return account;
	}

	@Override
	public Optional<Account> getAccountByToken(String token) {
		// TODO Auto-generated method stub
		Optional<User> user=Optional.of(new User());
			if(jwtService.isTokenExpired(token))
				throw new RuntimeException("You are unauthorized");
			try {
		    String username = jwtService.extractUsername(token);
		     user= userService.getUserByUsername(username);
			} catch (Exception e) {
			    throw new RuntimeException("Error processing request: " + e.getMessage());
			}
		    if (user.isEmpty()) {
		        throw new RuntimeException("You are unauthorized");
		    }
		    Optional<Account> account = getAccountByUserId(user.get().getId());
		    if (account.isEmpty()) {
		        throw new RuntimeException("This user has no account");
		    }
		    return account;	
	}

	@Override
	public void transferFunds(String token,TransferRequest transferRequest) {
		if(jwtService.isTokenExpired(token))
			throw new RuntimeException("This token is expired");
		Optional<Account> sendingAccount=Optional.of(new Account());
		Optional<Account> recievingAccount=Optional.of(new Account());
		sendingAccount=accountRepository.findByAccountNumber(transferRequest.getAccountNumberFrom());
		recievingAccount=accountRepository.findByAccountNumber(transferRequest.getAccountNumberTo().replace("-", ""));
		if(sendingAccount.isEmpty()||recievingAccount.isEmpty())
			throw new RuntimeException("This account is invalid");
		if(transferRequest.getAmount()<0)
			throw new RuntimeException("You must transfer positive amount");
		if(transferRequest.getAmount()>sendingAccount.get().getBalance())
			throw new RuntimeException("You have insufficient amount");
		
		sendingAccount.get().setBalance(sendingAccount.get().getBalance()-transferRequest.getAmount());
		recievingAccount.get().setBalance(recievingAccount.get().getBalance()+transferRequest.getAmount());
		accountRepository.saveAll(List.of(
		        sendingAccount.get(),
		        recievingAccount.get()
		));
		
		

		
	}
	
//	@Override
//	public Optional<Account> transferFundsByToken(String token, Double amount){
//		Optional<User> user=Optional.of(new User());
//		if(jwtService.isTokenExpired(token))
//			throw new RuntimeException("You are unauthorized");
//		try {
//	    String username = jwtService.extractUsername(token);
//	     user= userService.getUserByUsername(username);
//		} catch (Exception e) {
//		    throw new RuntimeException("Error processing request: " + e.getMessage());
//		}
//	    if (user.isEmpty()) {
//	        throw new RuntimeException("You are unauthorized");
//	    }
//	    Optional<Account> account = getAccountByUserId(user.get().getId());
//	    if (account.isEmpty()) {
//	        throw new RuntimeException("This user has no account");
//	    }
//	    return account;	
//	}

}
