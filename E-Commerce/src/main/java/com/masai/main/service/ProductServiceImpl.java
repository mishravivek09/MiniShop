package com.masai.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.main.entity.Product;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.myenum.Categories;
import com.masai.main.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository prepo;

	@Override
	public List<Product> addAllProducts(List<Product> products) {
		products.forEach(p->{
			prepo.save(p);
		});
		return products;
	}

	@Override
	public Product addProduct(Product product) {
		return prepo.save(product);
	}

	@Override
	public Product updateProductDetails(Product product) {
		Optional<Product> opt=prepo.findById(product.getProductId());
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Product not exist by id : "+product.getProductId());
		}
		return prepo.save(product);
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> opt=prepo.findById(id);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Product not exist by id : "+id);
		}
		return opt.get();
	}

	@Override
	public Product deleteById(int id) {
		Optional<Product> opt=prepo.findById(id);
		if(!opt.isPresent()) {
			throw new MyRuntimeExceptions("Product not exist by id : "+id);
		}
		Product product=opt.get();
		prepo.delete(product);
		
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list=prepo.findAll();
		
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No products exist...");
		}
		
		return list;
	}

	@Override
	public List<Product> getByCategory(Categories category) {
		List<Product> list=prepo.findByCategory(category);
		
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No products exist...");
		}
		
		return list;
	}

	@Override
	public String removeAllProduct() {
		
		prepo.deleteAll();
		
		return "All product deleted successfully..";
	}

	@Override
	public List<Product> searchByName(String name) {
		List<Product> list=prepo.searchByProductName(name);
		
		if(list.isEmpty()) {
			throw new MyRuntimeExceptions("No products exist...");
		}
		
		return list;
	}
	
}
