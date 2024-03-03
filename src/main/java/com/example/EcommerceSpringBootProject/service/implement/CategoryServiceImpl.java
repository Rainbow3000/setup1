package com.example.EcommerceSpringBootProject.service.implement;

import com.example.EcommerceSpringBootProject.entity.Category;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.repository.CategoryRepository;
import com.example.EcommerceSpringBootProject.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);

    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new NotFoundException("Not found category id");
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean update(Category categoryRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Not found category id"));
        category.setCategoryImg(categoryRequest.getCategoryImg());
        category.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Boolean delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Not found category id"));
        categoryRepository.delete(category);
        return true;
    }
}
