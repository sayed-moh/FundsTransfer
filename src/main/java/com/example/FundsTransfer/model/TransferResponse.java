package com.example.FundsTransfer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransferResponse {
	private String accountNumberTo;
	private String userName;
	private Double balance;
}
