package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, String>{
	
}
