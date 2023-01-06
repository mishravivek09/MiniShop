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

import com.masai.main.dto.AdminLoginDto;
import com.masai.main.entity.Admin;
import com.masai.main.service.AdminService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService service;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin){
		return new ResponseEntity<Admin>(service.registerAdmin(admin),HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin){
		return new ResponseEntity<Admin>(service.updateAdmin(admin),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Admin> deleteAdmin(@RequestParam String email){
		return new ResponseEntity<Admin>(service.deleteAdmin(email),HttpStatus.OK);
	}
	@DeleteMapping("/delete/all")
	public ResponseEntity<String> deleteAllAdmin(){
		return new ResponseEntity<String>(service.deleteAll(),HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@Valid @RequestBody AdminLoginDto dto){
		return new ResponseEntity<Admin>(service.loginAdmin(dto),HttpStatus.OK);
	}
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutAdmin(@RequestParam String uuid){
		return new ResponseEntity<String>(service.logoutAdmin(uuid),HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Admin>> getAllAdminList(){
		return new ResponseEntity<List<Admin>>(service.getAllAdminList(),HttpStatus.OK);
	}
}
