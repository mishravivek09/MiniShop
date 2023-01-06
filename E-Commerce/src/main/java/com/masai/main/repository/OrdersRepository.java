package com.masai.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.main.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
