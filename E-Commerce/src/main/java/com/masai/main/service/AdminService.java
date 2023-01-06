package com.masai.main.service;

import java.util.List;

import com.masai.main.dto.AdminLoginDto;
import com.masai.main.entity.Admin;

public interface AdminService {
	public Admin registerAdmin(Admin admin);
	public Admin updateAdmin(Admin admin);
	public Admin deleteAdmin(String email);
	public String deleteAll();
	public Admin loginAdmin(AdminLoginDto dto);
	public String logoutAdmin(String uuid);
	public List<Admin> getAllAdminList();
}
