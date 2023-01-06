package com.masai.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.main.dto.AccountDto;
import com.masai.main.dto.TransactionDto;
import com.masai.main.dto.TransactionResDto;
import com.masai.main.entity.Account;
import com.masai.main.entity.Customer;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.repository.BankRepository;
import com.masai.main.repository.CustomerRepository;
@Service
public class BankServiceImpl implements BankService{
	@Autowired
	private BankRepository brepo;
	@Autowired
	private CustomerRepository crepo;

	@Override
	public Account addCustomer(AccountDto acc) {
		Optional<Customer> opt=crepo.findById(acc.getCustomerid());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Customer is not valid..");
		}
		Customer cus=opt.get();
		Account ac=acc.getAccount();
		if(cus.getAccount()!=null) {
			throw new MyRuntimeExceptions("Account already added by customer : "+cus.getName());
		}
		ac.setCustomer(cus);
		cus.setAccount(acc.getAccount());
		
		return brepo.save(ac);
	}
	@Override
	public TransactionResDto withdrawMoney(TransactionDto dto) {
		Optional<Customer> opt=crepo.findById(dto.getCustomerid());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Customer is not valid..");
		}
		Customer cus=opt.get();
		Account acc=cus.getAccount();
		if(acc==null) {
			throw new MyRuntimeExceptions("Account not added by customer : "+cus.getName());
		}
		if(!(acc.getPin_number().equals(dto.getPin()))) {
			throw new MyRuntimeExceptions("Invalid pin number..");
		}
		if(acc.getBalance()<1) {
			throw new MyRuntimeExceptions("Insufficient balance..");
		}
		if(dto.getAmount()<1) {
			throw new MyRuntimeExceptions("Amount should be grater than 1");
		}
		if(acc.getBalance() < dto.getAmount()) {
			throw new MyRuntimeExceptions("Insufficient balance");
		}
		acc.setBalance(acc.getBalance()-dto.getAmount());
		brepo.save(acc);
		
		TransactionResDto res=new TransactionResDto();
		res.setAccountNo(acc.getAccountNo());
		res.setAmount(dto.getAmount());
		res.setBankName(acc.getBank_Name());
		res.setDateTime(LocalDateTime.now());
		res.setIfsc(acc.getIfsc());
		res.setTotalBalance(acc.getBalance());
		return res;
	}
	@Override
	public TransactionResDto depositMoney(TransactionDto dto) {
		Optional<Customer> opt=crepo.findById(dto.getCustomerid());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Customer is not valid..");
		}
		Customer cus=opt.get();
		Account acc=cus.getAccount();
		if(acc==null) {
			throw new MyRuntimeExceptions("Account not added by customer : "+cus.getName());
		}
		if(!(acc.getPin_number().equals(dto.getPin()))) {
			throw new MyRuntimeExceptions("Invalid pin number..");
		}
		if(dto.getAmount()<1) {
			throw new MyRuntimeExceptions("Cannot add money less than 1");
		}
		if(dto.getAmount()>50000) {
			throw new MyRuntimeExceptions("Cannot add balance more than 50000");
		}
		if(acc.getBalance()==0.0) {
			acc.setBalance(dto.getAmount());
		}else {
			acc.setBalance(acc.getBalance()+dto.getAmount());
		}
		brepo.save(acc);
		TransactionResDto res=new TransactionResDto();
		res.setAccountNo(acc.getAccountNo());
		res.setAmount(dto.getAmount());
		res.setBankName(acc.getBank_Name());
		res.setDateTime(LocalDateTime.now());
		res.setIfsc(acc.getIfsc());
		res.setTotalBalance(acc.getBalance());
		return res;
	}
	@Override
	public Account checkBalance(int customerId,String pin) {
		Optional<Customer> opt=crepo.findById(customerId);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Customer is not valid..");
		}
		Customer cus=opt.get();
		if(cus.getAccount()==null) {
			throw new MyRuntimeExceptions("Customer is not added bank account..");
		}
		Account acc=cus.getAccount();
		if(!(acc.getPin_number().equals(pin))) {
			throw new MyRuntimeExceptions("Invalid pin number..");
		}
		return acc;
	}
	@Override
	public Account deleteAccountById(int id) {
		Optional<Account> opt=brepo.findById(id);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid account number..");
		}
		Account acc=opt.get();
		brepo.delete(acc);
		
		return acc;
	}
	@Override
	public String deleteAllAccount() {
		brepo.deleteAll();
		return "All bank account deleted successfully..";
	}
	@Override
	public List<Account> getAllAccount() {
		List<Account> list=brepo.findAll();
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No bank account exist..");
		}
		return list;
	}
	@Override
	public Account findAccountByCustomerId(int id) {
		Optional<Customer> opt=crepo.findById(id);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid customer id..");
		}
		Customer cus=opt.get();
		Account acc=cus.getAccount();
		if(acc==null) {
			throw new MyRuntimeExceptions("Account not exist..");
		}
		return acc;
	}
}
