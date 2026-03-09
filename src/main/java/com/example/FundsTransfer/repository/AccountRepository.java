package com.example.FundsTransfer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FundsTransfer.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUserId(Long userId);
	Optional<Account> findByAccountNumber(String accountNumber);


}
