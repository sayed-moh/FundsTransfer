package com.example.FundsTransfer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class TransferRequest {
	  private String accountNumberFrom;
	  private String accountNumberTo;
	  private Double amount;
}

  