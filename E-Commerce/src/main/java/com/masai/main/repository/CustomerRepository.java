package com.masai.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.main.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Customer findByEmail(String email);
}
