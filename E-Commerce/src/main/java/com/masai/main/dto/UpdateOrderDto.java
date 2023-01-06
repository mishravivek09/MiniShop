package com.masai.main.dto;

import com.masai.main.myenum.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {
	private int orderid;
	private OrderStatus status;
}
