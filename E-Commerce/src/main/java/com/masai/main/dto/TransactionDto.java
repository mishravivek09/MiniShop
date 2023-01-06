package com.masai.main.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
	@NotNull(message="please enter pin")
	private String pin;
	@NotNull(message = "please enter customer id")
	private int customerid;
	@NotNull(message = "please enter amount")
	private double amount;
}
