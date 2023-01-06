package com.masai.main.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.main.myenum.Bank;
import com.masai.main.myenum.Ifsc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNo;
	private double balance;
	private Ifsc ifsc;
	private Bank Bank_Name;
	private String pin_number;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
	@JsonIgnore
	private Customer customer;
}
