package com.masai.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.main.entity.Product;
import com.masai.main.myenum.Categories;
import com.masai.main.service.ProductService;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/add/all")
	public ResponseEntity<List<Product>> addListOfProductsHandler(@RequestBody List<Product> list){
		return new ResponseEntity<>(service.addAllProducts(list),HttpStatus.CREATED);
	}
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
		return new ResponseEntity<Product>(service.addProduct(product),HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product){
		return new ResponseEntity<Product>(service.updateProductDetails(product),HttpStatus.OK);
	}
	@GetMapping("/get")
	public ResponseEntity<Product> getById(@RequestParam int id){
		return new ResponseEntity<Product>(service.getProductById(id),HttpStatus.OK);
	}
	@DeleteMapping("/remove")
	public ResponseEntity<Product> deleteById(@RequestParam int id){
		return new ResponseEntity<Product>(service.deleteById(id),HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<List<Product>>(service.getAllProduct(),HttpStatus.OK);
	}
	@GetMapping("/category")
	public ResponseEntity<List<Product>> getAllProductByCat(@RequestParam Categories cat){
		return new ResponseEntity<List<Product>>(service.getByCategory(cat),HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String query){
		return new ResponseEntity<List<Product>>(service.searchByName(query),HttpStatus.OK);
	}
	@DeleteMapping("/remove/all")
	public ResponseEntity<String> deleteAllProduct(){
		return new ResponseEntity<String>(service.removeAllProduct(),HttpStatus.OK);
	}
}
