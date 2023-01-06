package com.masai.main.service;

import com.masai.main.dto.AllOrdersDto;
import com.masai.main.dto.OrderDto;
import com.masai.main.dto.OrderResDto;
import com.masai.main.dto.UpdateOrderDto;
import com.masai.main.entity.CartProduct;
import com.masai.main.entity.Orders;
import com.masai.main.entity.Product;

import java.util.List;

public interface OrderService {
	public OrderResDto placeOrder(OrderDto dto);
	public Orders cancelOrder(String email,int orderid);
	public Orders checkStatus(String email,int orderid);
	public List<AllOrdersDto> getAllOrders(String email);
	public List<CartProduct> getAllOrdersProduct(int orderid);
	public List<Orders> allOrdersList();
	public Orders updateStatus(UpdateOrderDto dto);
	public Orders deleteOrderById(int orderid);
	public String deleteAllOrders();
}
