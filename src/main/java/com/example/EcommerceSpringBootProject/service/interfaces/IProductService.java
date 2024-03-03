package com.example.EcommerceSpringBootProject.service.interfaces;

import com.example.EcommerceSpringBootProject.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IProductService {
    Product save(Product product, Long categoryId);
    List<Product> findAll();
    Set<Product> findAllByCategoryId(Long categoryId);
    Boolean update(Product product, Long travelId);
    Boolean delete(Long travelId);
}
