package com.masai.main.service;

import java.util.List;

import com.masai.main.entity.Product;
import com.masai.main.myenum.Categories;

public interface ProductService {
	public List<Product> addAllProducts(List<Product> products);
	public Product addProduct(Product product);
	public Product updateProductDetails(Product product);
	public Product getProductById(int id);
	public Product deleteById(int id);
	public List<Product> getAllProduct();
	public List<Product> getByCategory(Categories category);
	public String removeAllProduct();
	public List<Product> searchByName(String name);
}
