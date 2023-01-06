package com.masai.main.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masai.main.myenum.Categories;
import com.masai.main.myenum.OrderStatus;
import com.masai.main.myenum.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllOrdersDto {
	private int orderid;
	private String productName;
	private String image;
	private Categories category;
	private double rating;
	private OrderStatus status;
	private PaymentMethod payMethod;
	private long quantity;
	private double price;
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss")
	private LocalDateTime dateTime;
}
