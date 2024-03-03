package com.example.EcommerceSpringBootProject.service.implement;

import com.example.EcommerceSpringBootProject.entity.Category;
import com.example.EcommerceSpringBootProject.entity.Product;
import com.example.EcommerceSpringBootProject.exception.NotFoundException;
import com.example.EcommerceSpringBootProject.repository.CategoryRepository;
import com.example.EcommerceSpringBootProject.repository.ProductRepository;
import com.example.EcommerceSpringBootProject.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Product save(Product productRequest, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new NotFoundException("Not found category id"));
        productRequest.setCategory(category);
        return productRepository.save(productRequest);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Set<Product> findAllByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new NotFoundException("category id "+categoryId+" not found !"));

        return category.getTravel();
    }

    @Override
    public Boolean update(Product productRequest, Long travelId) {
        Product product = productRepository.findById(travelId).orElseThrow(()-> new NotFoundException("Not found travel id"));
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductImg(productRequest.getProductImg());
        product.setProductStatus(productRequest.getProductStatus());
        product.setProductPriceNew(productRequest.getProductPriceNew());
        product.setProductPriceOld(productRequest.getProductPriceOld());
        productRepository.save(product);
        return true;
    }


    @Override
    public Boolean delete(Long travelId) {
        Product product = productRepository.findById(travelId).orElseThrow(()-> new NotFoundException("Not found travel id"));
        productRepository.delete(product);
        return true;
    }
}
