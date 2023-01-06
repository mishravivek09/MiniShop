package com.masai.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.main.dto.CustomerDto;
import com.masai.main.dto.CustomerResDto;
import com.masai.main.dto.LoginDto;
import com.masai.main.dto.LoginResDto;
import com.masai.main.entity.Customer;
import com.masai.main.service.CustomerService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	@PostMapping("/register")
	public ResponseEntity<CustomerResDto> registerCustomer(@Valid @RequestBody CustomerDto customer){
		return new ResponseEntity<CustomerResDto>(service.addCustomer(customer),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResDto> validateCustomer(@Valid @RequestBody LoginDto dto){
		return new ResponseEntity<LoginResDto>(service.validateCustomer(dto),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CustomerResDto> updateCustomer(@Valid @RequestBody CustomerDto dto,@RequestParam String uuid){
		return new ResponseEntity<CustomerResDto>(service.updateCustomer(dto, uuid),HttpStatus.OK);
	}
	@DeleteMapping("/logout")
	public ResponseEntity<String> logOutCustomer(@RequestParam String uuid){
		return new ResponseEntity<String>(service.logOutUser(uuid),HttpStatus.OK);
	}
	@DeleteMapping("/remove")
	public ResponseEntity<CustomerResDto> deleteCustomer(@RequestParam String email){
		return new ResponseEntity<CustomerResDto>(service.deleteCustomer(email),HttpStatus.OK);
	}
	@DeleteMapping("/delete/all")
	public ResponseEntity<String> deleteAllCustomers(){
		return new ResponseEntity<String>(service.deleteAllCustomer(),HttpStatus.OK);
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return new ResponseEntity<List<Customer>>(service.getAllCustomer(),HttpStatus.OK);
	}
}
