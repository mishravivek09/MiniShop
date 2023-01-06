package com.masai.main.service;

import java.util.List;

import com.masai.main.dto.AccountDto;
import com.masai.main.dto.TransactionDto;
import com.masai.main.dto.TransactionResDto;
import com.masai.main.entity.Account;

public interface BankService {
	public Account addCustomer(AccountDto acc);
	public TransactionResDto withdrawMoney(TransactionDto dto);
	public TransactionResDto depositMoney(TransactionDto dto);
	public Account checkBalance(int customerId,String pin);
	public Account deleteAccountById(int id);
	public String deleteAllAccount();
	public List<Account> getAllAccount();
	public Account findAccountByCustomerId(int id);
}
