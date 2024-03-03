package com.example.EcommerceSpringBootProject.dto;

import java.util.ArrayList;
import java.util.List;

public class TestDto {
    private List<Integer> productId = new ArrayList<>();

    public TestDto() {

    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }
}
