package com.masai.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.main.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByEmail(String email);
}
