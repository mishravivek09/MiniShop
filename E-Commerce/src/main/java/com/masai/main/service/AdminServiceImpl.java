package com.masai.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.main.dto.AdminLoginDto;
import com.masai.main.entity.Admin;
import com.masai.main.entity.CurrentUserSession;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.repository.AdminRepository;
import com.masai.main.repository.SessionRepository;

import net.bytebuddy.utility.RandomString;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepository arepo;
	@Autowired
	private SessionRepository srepo;

	@Override
	public Admin registerAdmin(Admin admin) {
		Admin ad=arepo.findByEmail(admin.getEmail());
		if(ad!=null) {
			throw new MyRuntimeExceptions("User is already registered with email : "+admin.getEmail());
		}
		return arepo.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Admin ad=arepo.findByEmail(admin.getEmail());
		if(ad==null) {
			throw new MyRuntimeExceptions("User is not registered with email : "+admin.getEmail());
		}
		return arepo.save(admin);
	}

	@Override
	public Admin deleteAdmin(String email) {
		Admin ad=arepo.findByEmail(email);
		if(ad==null) {
			throw new MyRuntimeExceptions("User is not exist with email : "+email);
		}
		arepo.delete(ad);
		return ad;
	}

	@Override
	public String deleteAll() {
		arepo.deleteAll();
		return "All users deleted successfully..";
	}

	@Override
	public Admin loginAdmin(AdminLoginDto dto) {
		Admin ad=arepo.findByEmail(dto.getEmail());
		if(ad==null) {
			throw new MyRuntimeExceptions("User is not registered with email : "+dto.getEmail());
		}
		Optional<CurrentUserSession> opt=srepo.findById(ad.getId());
		if(opt.isPresent()) {
			srepo.delete(opt.get());
		}
		if(!ad.getPassword().equals(dto.getPassword())) {
			throw new MyRuntimeExceptions("Password is incorrect..");
		}
		String key=RandomString.make(6);
		CurrentUserSession currSession=new CurrentUserSession();
		currSession.setDateTime(LocalDateTime.now());
		currSession.setUserId(ad.getId());
		currSession.setUuid(key);
		
		srepo.save(currSession);
		return ad;
	}

	@Override
	public String logoutAdmin(String uuid) {
		CurrentUserSession session=srepo.findByUuid(uuid);
		if(session==null) {
			throw new MyRuntimeExceptions("Invalid session uuid..");
		}
		Optional<Admin> opt=arepo.findById(session.getUserId());
		if(opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid user..");
		}
		Admin usr=opt.get();
		if(usr.getId()!=session.getUserId()) {
			throw new MyRuntimeExceptions("Invalid user..");
		}
		srepo.delete(session);
		return "Logged out successfully..";
	}

	@Override
	public List<Admin> getAllAdminList() {
		List<Admin> list=arepo.findAll();
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No Admins found..");
		}
		return list;
	}
}
