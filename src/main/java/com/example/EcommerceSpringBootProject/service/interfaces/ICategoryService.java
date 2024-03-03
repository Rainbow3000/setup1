package com.example.EcommerceSpringBootProject.service.interfaces;

import com.example.EcommerceSpringBootProject.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    Category save(Category category);
    Category findById(Long id);
    List<Category> findAll();
    Boolean update (Category category,Long categoryId);
    Boolean delete (Long categoryId);

}
