package com.masai.main.controller;

import java.util.List;

import com.masai.main.entity.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.main.dto.AllOrdersDto;
import com.masai.main.dto.OrderDto;
import com.masai.main.dto.OrderResDto;
import com.masai.main.dto.UpdateOrderDto;
import com.masai.main.entity.Orders;
import com.masai.main.entity.Product;
import com.masai.main.service.OrderService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@PostMapping("/place")
	public ResponseEntity<OrderResDto> placeOrder(@RequestBody OrderDto dto){
		return new ResponseEntity<OrderResDto>(service.placeOrder(dto),HttpStatus.OK);
	}
	@PutMapping("/cancel/{email}")
	public ResponseEntity<Orders> cancelOrder(@PathVariable String email,@RequestParam int orderid){
		return new ResponseEntity<Orders>(service.cancelOrder(email,orderid),HttpStatus.OK);
	}
	@GetMapping("/status/{email}")
	public ResponseEntity<Orders> checkStatus(@PathVariable String email,@RequestParam int orderid){
		return new ResponseEntity<Orders>(service.checkStatus(email,orderid),HttpStatus.OK);
	}
	@GetMapping("/customer")
	public ResponseEntity<List<AllOrdersDto>> getAllOrdersByCustomerId(@RequestParam String email){
		return new ResponseEntity<List<AllOrdersDto>>(service.getAllOrders(email),HttpStatus.OK);
	}
	@GetMapping("/products")
	public ResponseEntity<List<CartProduct>> getAllOrdersOfProducts(@RequestParam int orderid){
		return new ResponseEntity<List<CartProduct>>(service.getAllOrdersProduct(orderid),HttpStatus.OK);
	}
	@DeleteMapping("/delete/all")
	public ResponseEntity<String> deleteAllOrders(){
		return new ResponseEntity<String>(service.deleteAllOrders(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Orders> deleteOrderById(@RequestParam int orderid){
		return new ResponseEntity<Orders>(service.deleteOrderById(orderid),HttpStatus.OK);
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<Orders>> getAllOrders(){
		return new ResponseEntity<List<Orders>>(service.allOrdersList(),HttpStatus.OK);
	}
	@PutMapping("/update/status")
	public ResponseEntity<Orders> updateOrderStatus(@RequestBody UpdateOrderDto dto){
		return new ResponseEntity<Orders>(service.updateStatus(dto),HttpStatus.OK);
	}
}
