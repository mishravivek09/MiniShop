package com.masai.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.main.myenum.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private Gender gender;
	@Embedded
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_no")
	@JsonIgnore
	private Account account;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	@JsonIgnore
	private List<Orders> order=new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	@JsonIgnore
	private Cart cart;
}
