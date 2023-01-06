package com.masai.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.main.entity.Account;

public interface BankRepository extends JpaRepository<Account, Integer>{

}
