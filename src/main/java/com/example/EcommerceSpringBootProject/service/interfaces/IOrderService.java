package com.example.EcommerceSpringBootProject.service.interfaces;

import com.example.EcommerceSpringBootProject.entity.Order;
import com.example.EcommerceSpringBootProject.entity.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IOrderService {
    public void save(Order order, OrderDetails orderDetails, Long userId,Long travelId);
    List<Order> findAll();

    List<OrderDetails> findAllByUserId(Long userId);
    Boolean update (Long orderId);

    Boolean delete (Long orderId);

    Order findById(Long id);

    OrderDetails orderDetailsFindById(Long id);

    List<OrderDetails> findAllOrderDetails();

}
