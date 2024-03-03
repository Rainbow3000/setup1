package com.example.EcommerceSpringBootProject.repository;

import com.example.EcommerceSpringBootProject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
