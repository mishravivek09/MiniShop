package com.masai.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.masai.main.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.main.dto.CustomerDto;
import com.masai.main.dto.CustomerResDto;
import com.masai.main.dto.LoginDto;
import com.masai.main.dto.LoginResDto;
import com.masai.main.entity.CurrentUserSession;
import com.masai.main.entity.Customer;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.repository.CustomerRepository;
import com.masai.main.repository.SessionRepository;

import net.bytebuddy.utility.RandomString;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository crepo;
	@Autowired
	private SessionRepository srepo;

	@Override
	public CustomerResDto addCustomer(CustomerDto customer) {
		Customer cus=crepo.findByEmail(customer.getEmail());
		if(cus!=null) {
			throw new MyRuntimeExceptions("Customer already registered..");
		}
		Customer cust=new Customer();
		cust.setName(customer.getName());
		cust.setEmail(customer.getEmail());
		cust.setPassword(customer.getPassword());
		cust.setAddress(customer.getAddress());
		cust.setGender(customer.getGender());

		Cart cart=new Cart();
		cust.setCart(cart);
		cart.setCustomer(cust);
		
		crepo.save(cust);
		
		CustomerResDto dto=new CustomerResDto();
		dto.setId(cust.getId());
		dto.setDateTime(LocalDateTime.now());
		dto.setEmail(cust.getEmail());
		dto.setName(cust.getName());
		dto.setMessage("Signed up Successfully !");
		return dto;
	}

	@Override
	public CustomerResDto updateCustomer(CustomerDto customer,String uuid) {
		Customer cus=crepo.findByEmail(customer.getEmail());
		if(cus==null) {
			throw new MyRuntimeExceptions("Customer not registered..");
		}
		
		CurrentUserSession session=srepo.findByUuid(uuid);
		if(session==null) {
			throw new MyRuntimeExceptions("Please login first..");
		}else if(cus.getId()!=session.getUserId()) {
			throw new MyRuntimeExceptions("Please login first..");
		}
		
		cus.setName(customer.getName());
		cus.setPassword(customer.getPassword());
		cus.setAddress(customer.getAddress());
		
		crepo.save(cus);
		
		CustomerResDto dto=new CustomerResDto();
		dto.setDateTime(LocalDateTime.now());
		dto.setEmail(cus.getEmail());
		dto.setMessage("Updated Successfully..");
		dto.setId(cus.getId());
		dto.setName(customer.getName());
		
		
		return dto;
	}

	@Override
	public LoginResDto validateCustomer(LoginDto login) {
		Customer cus=crepo.findByEmail(login.getEmail());
		if(cus==null) {
			throw new MyRuntimeExceptions("Email is incorrect..");
		}
		Optional<CurrentUserSession> opt=srepo.findById(cus.getId());
		if(opt.isPresent()) {
			CurrentUserSession session=opt.get();
			srepo.delete(session);
		}
		if(!cus.getPassword().equals(login.getPassword())) {
			throw new MyRuntimeExceptions("Password is incorrect..");
		}
		String key=RandomString.make(6);
		CurrentUserSession currSession=new CurrentUserSession();
		currSession.setDateTime(LocalDateTime.now());
		currSession.setUserId(cus.getId());
		currSession.setUuid(key);
		
		srepo.save(currSession);
		
		LoginResDto dto=new LoginResDto();
		dto.setDateTime(LocalDateTime.now());
		dto.setEmail(login.getEmail());
		dto.setMessage("Logged in Successfully !");
		dto.setName(cus.getName());
		dto.setSessionId(key);
		dto.setCustomerId(cus.getId());
		dto.setCartId(cus.getCart().getId());
		return dto;
	}

	@Override
	public String logOutUser(String uuid) {
		CurrentUserSession session=srepo.findByUuid(uuid);
		if(session==null) {
			throw new MyRuntimeExceptions("Invalid session uuid..");
		}
		Optional<Customer> opt=crepo.findById(session.getUserId());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid user..");
		}
		Customer usr=opt.get();
		if(usr.getId()!=session.getUserId()) {
			throw new MyRuntimeExceptions("Invalid user..");
		}
		srepo.delete(session);
		return "Logged out successfully..";
	}

	@Override
	public CustomerResDto deleteCustomer(String email) {
		Customer cus=crepo.findByEmail(email);
		if(cus==null) {
			throw new MyRuntimeExceptions("Customer not found by email : "+email);
		}
		crepo.delete(cus);
		
		CustomerResDto dto=new CustomerResDto();
		dto.setDateTime(LocalDateTime.now());
		dto.setEmail(cus.getEmail());
		dto.setMessage("Deleted Successfully..");
		dto.setId(cus.getId());
		dto.setName(cus.getName());
		
		return dto;
	}

	@Override
	public String deleteAllCustomer() {
		crepo.deleteAll();
		return "All customer deleted successfully..";
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list=crepo.findAll();
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No customers exist..");
		}
		return list;
	}
}
