package com.masai.main.service;

import com.masai.main.dto.AllOrdersDto;
import com.masai.main.dto.OrderDto;
import com.masai.main.dto.OrderResDto;
import com.masai.main.dto.UpdateOrderDto;
import com.masai.main.entity.*;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.myenum.OrderStatus;
import com.masai.main.repository.CartRepository;
import com.masai.main.repository.CustomerRepository;
import com.masai.main.repository.OrdersRepository;
import com.masai.main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrdersRepository ordrepo;
	@Autowired
	private CustomerRepository crepo;
	@Autowired
	private ProductRepository prepo;
	@Autowired
	private CartRepository cartRepo;
	@Override
	public OrderResDto placeOrder(OrderDto dto) {
		Orders orders=new Orders();
		Customer customer=crepo.findByEmail(dto.getCustomerEmail());
		if(customer==null){
			throw new MyRuntimeExceptions("Customer is not exist with email : "+dto.getCustomerEmail());
		}
		Cart cart=customer.getCart();
		List<CartProduct> cartItems=cart.getProducts();
		if(cartItems.isEmpty()){
			throw new MyRuntimeExceptions("Cart is empty..!");
		}
		List<Product> productList=new ArrayList<>();
		int quantity=0,totalPrice=0;
		for(CartProduct p:cartItems){
			quantity+=p.getQuantity();
			totalPrice+=(p.getPrice()*p.getQuantity());
			Optional<Product> optional=prepo.findById(p.getProductId());
			if(!optional.isPresent()){
				throw new MyRuntimeExceptions("Product not exist with id : "+p.getProductId());
			}
			productList.add(optional.get());
		}
		for(int i=0;i<cartItems.size();i++){
			productList.get(i).setQuantity(productList.get(i).getQuantity()-cartItems.get(i).getQuantity());
			productList.get(i).getOrder().add(orders);
		}
		orders.getProduct().addAll(cartItems);
		orders.setPayMethod(dto.getPaymentMethod());
		orders.setCustomer(customer);
		orders.setDateTime(LocalDateTime.now());
		orders.setQuantity(quantity);
		orders.setTotalPrice(totalPrice);
		orders.setStatus(OrderStatus.PENDING);
		customer.getOrder().add(orders);


		ordrepo.save(orders);

		cartItems.clear();
		cartRepo.save(cart);
		for (Product p:productList){
			prepo.save(p);
		}
		OrderResDto res=new OrderResDto();
		res.setDateTime(LocalDateTime.now());
		res.setPayMethod(dto.getPaymentMethod().toString());
		res.setQuantity(quantity);
		res.setStatus("Order Placed");
		res.setPrice(totalPrice);
		res.setOrderid(orders.getId());

		return res;
	}

	@Override
	public Orders cancelOrder(String email,int orderid) {
		Customer cus=crepo.findByEmail(email);
		if(cus==null) {
			throw new MyRuntimeExceptions("Customer not registered..");
		}
		List<Orders> list= cus.getOrder();

		Optional<Orders> opt=ordrepo.findById(orderid);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		Orders ord=opt.get();

		if(!(list.contains(ord))) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		if(ord.getStatus().equals(OrderStatus.COMPLETED)){
			throw new MyRuntimeExceptions("Order is already completed..");
		}
		ord.setStatus(OrderStatus.CANCELLED);
		ordrepo.save(ord);

		return ord;
	}

	@Override
	public Orders checkStatus(String email,int orderid) {

		Customer cus=crepo.findByEmail(email);
		if(cus==null) {
			throw new MyRuntimeExceptions("Customer not registered..");
		}
		List<Orders> list= cus.getOrder();

		Optional<Orders> opt=ordrepo.findById(orderid);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		Orders ord=opt.get();

		if(!(list.contains(ord))) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}

		return ord;
	}

	@Override
	public List<AllOrdersDto> getAllOrders(String email) {
		Customer cus=crepo.findByEmail(email);
		if(cus==null) {
			throw new MyRuntimeExceptions("Customer is not exist with email : "+email);
		}
		List<Orders> res=cus.getOrder();
		if(res.isEmpty()) {
			throw new MyRuntimeExceptions("No orders placed by : "+email);
		}
		List<AllOrdersDto> list=new ArrayList<>();
		res.forEach(o->{
			List<CartProduct> plist= o.getProduct();
			plist.forEach(p->{
				AllOrdersDto dto=new AllOrdersDto();
				dto.setOrderid(o.getId());
				dto.setDateTime(o.getDateTime());
				dto.setQuantity(p.getQuantity());
				dto.setPayMethod(o.getPayMethod());
				dto.setStatus(o.getStatus());
				dto.setPrice(p.getPrice());
				dto.setProductName(p.getProductName());
				dto.setCategory(p.getCategory());
				dto.setImage(p.getImage());
				dto.setRating(p.getRating());
				list.add(dto);
			});
		});
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("Order list is empty..");
		}
		return list;
	}

	@Override
	public List<CartProduct> getAllOrdersProduct(int orderid) {
		Optional<Orders> opt=ordrepo.findById(orderid);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		Orders order=opt.get();
		List<CartProduct> list=order.getProduct();
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No orders placed for this product..");
		}
		return list;
	}

	@Override
	public List<Orders> allOrdersList() {
		List<Orders> list=ordrepo.findAll();
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No orders exist..");
		}
		return list;
	}

	@Override
	public Orders updateStatus(UpdateOrderDto dto) {
		Optional<Orders> opt=ordrepo.findById(dto.getOrderid());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		Orders ord=opt.get();
		ord.setStatus(dto.getStatus());
		ordrepo.save(ord);

		return ord;
	}

	@Override
	public Orders deleteOrderById(int orderid) {
		Optional<Orders> opt=ordrepo.findById(orderid);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Invalid order id..");
		}
		Orders ord=opt.get();
		ordrepo.delete(ord);

		return ord;
	}

	@Override
	public String deleteAllOrders() {
		ordrepo.deleteAll();
		return "All orders deleted successfully..";
	}
}
