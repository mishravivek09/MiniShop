package com.masai.main.service;

import java.util.List;

import com.masai.main.dto.CustomerDto;
import com.masai.main.dto.CustomerResDto;
import com.masai.main.dto.LoginDto;
import com.masai.main.dto.LoginResDto;
import com.masai.main.entity.Customer;

public interface CustomerService {
	public CustomerResDto addCustomer(CustomerDto customer);
	public CustomerResDto updateCustomer(CustomerDto customer,String uuid);
	public LoginResDto validateCustomer(LoginDto login);
	public String logOutUser(String uuid);
	public CustomerResDto deleteCustomer(String email);
	public String deleteAllCustomer();
	public List<Customer> getAllCustomer();
}
