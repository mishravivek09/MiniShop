package com.masai.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.main.dto.AccountDto;
import com.masai.main.dto.TransactionDto;
import com.masai.main.dto.TransactionResDto;
import com.masai.main.entity.Account;
import com.masai.main.service.BankService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/account")
public class BankAccountController {
	@Autowired
	private BankService service;
	@PostMapping("/register")
	public ResponseEntity<Account> addBankAccount(@Valid @RequestBody AccountDto dto){
		return new ResponseEntity<Account>(service.addCustomer(dto),HttpStatus.OK);
	}
	@PutMapping("/deposit")
	public ResponseEntity<TransactionResDto>  depositMoney(@Valid @RequestBody TransactionDto dto){
		return new ResponseEntity<TransactionResDto>(service.depositMoney(dto),HttpStatus.OK);
	}
	@PutMapping("/withdraw")
	public ResponseEntity<TransactionResDto> withdrawMoney(@Valid @RequestBody TransactionDto dto){
		return new ResponseEntity<TransactionResDto>(service.withdrawMoney(dto),HttpStatus.OK);
	}
	@GetMapping("/check/balance/{pin}")
	public ResponseEntity<Account> checkBalance(@RequestParam int customerId,@PathVariable String pin){
		return new ResponseEntity<Account>(service.checkBalance(customerId,pin),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Account> deleteAccountBynumber(@RequestParam int id){
		return new ResponseEntity<Account>(service.deleteAccountById(id),HttpStatus.OK);
	}
	@DeleteMapping("/remove/all")
	public ResponseEntity<String> deleteAllBankAccount(){
		return new ResponseEntity<String>(service.deleteAllAccount(),HttpStatus.OK);
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<Account>> getAllAccount(){
		return new ResponseEntity<List<Account>>(service.getAllAccount(),HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<Account> getAccountByIdHandler(@PathVariable int id){
		return new ResponseEntity<Account>(service.findAccountByCustomerId(id),HttpStatus.OK);
	}
}
