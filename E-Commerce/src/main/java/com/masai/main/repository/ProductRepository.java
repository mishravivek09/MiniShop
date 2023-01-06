package com.masai.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.main.entity.Product;
import com.masai.main.myenum.Categories;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	public List<Product> findByCategory(Categories cat);
	@Query("select p from Product p where lower(p.productName) like lower(concat('%', :nameToFind,'%'))")
	public List<Product> searchByProductName(@Param("nameToFind") String name);
}
