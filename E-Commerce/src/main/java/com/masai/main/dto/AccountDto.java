package com.masai.main.dto;

import javax.validation.constraints.NotNull;

import com.masai.main.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	@NotNull(message = "please enter customer id")
	private int customerid;
	@NotNull(message = "please enter account data properly")
	private Account account;
}
