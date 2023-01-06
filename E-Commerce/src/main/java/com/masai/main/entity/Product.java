package com.masai.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.main.myenum.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@NotNull(message = "please enter values")
	private String productName;
	@NotNull(message = "please enter values")
	private String image;
	@NotNull(message = "please enter values")
	private long quantity;
	@NotNull(message = "please enter values")
	private double price;
	private Categories category;
	@NotNull(message = "please enter values")
	private double rating;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	List<Orders> order=new ArrayList<>();
}
