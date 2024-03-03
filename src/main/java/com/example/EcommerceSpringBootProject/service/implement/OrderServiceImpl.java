package com.example.EcommerceSpringBootProject.service.implement;

import com.example.EcommerceSpringBootProject.entity.Order;
import com.example.EcommerceSpringBootProject.entity.OrderDetails;
import com.example.EcommerceSpringBootProject.entity.Product;
import com.example.EcommerceSpringBootProject.entity.User;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.repository.*;
import com.example.EcommerceSpringBootProject.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Override
    public void save(Order order, OrderDetails orderDetails,  Long userId, Long productId) {

        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new NotFoundException("User id "+ userId + "not found !");
        }

        Product product = productRepository.findById(productId).orElse(null);


        if(product == null){
            throw new NotFoundException("Travel id " + productId + "not found !");
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        order.setUser(user);

        order.setCreatedDate(date);
        order.setStatus("Đang chờ duyệt");
        orderDetails.setOrder(orderRepository.save(order));
        orderDetails.setStatus("Đang chờ duyệt");
        orderDetails.setProduct(product);
        orderDetails.setCreatedDate(date);

        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetails> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found !"));
        List<OrderDetails> orderDetailsResponse = new ArrayList<>();
        user.getOrders().forEach(item->{
            orderDetailsResponse.add(orderDetailsRepository.findById(item.getId()).orElse(null));
        });
        return orderDetailsResponse;
    }


    @Override
    public Boolean update(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Not found"));
        OrderDetails orderDetails = orderDetailsRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Not found"));
        order.setStatus("Đã duyệt");
        orderDetails.setStatus("Đã duyệt");
        orderRepository.save(order);
        orderDetailsRepository.save(orderDetails);
        return true ;
    }

    @Override
    public Boolean delete(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Not found order id"));
        orderDetailsRepository.delete(order.getOrderDetails());
        orderRepository.delete(order);
        return true;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDetails orderDetailsFindById(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderDetails> findAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }
}
