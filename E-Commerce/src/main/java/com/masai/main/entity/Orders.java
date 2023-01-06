package com.masai.main.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.main.myenum.OrderStatus;
import com.masai.main.myenum.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern="E, dd MMM yyyy HH:mm:ss")
	private LocalDateTime dateTime;
	private OrderStatus status;
	private PaymentMethod payMethod;
	private long quantity;
	private double totalPrice;
	@ElementCollection
	private List<CartProduct> product=new ArrayList<>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
}
