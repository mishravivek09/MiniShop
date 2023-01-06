package com.masai.main.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.masai.main.myenum.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	@Email(message = "Enter email id")
	private String customerEmail;
	private PaymentMethod paymentMethod;
}
