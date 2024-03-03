package com.example.EcommerceSpringBootProject.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ProductDto extends BaseDto {

    @NotNull
    private String productName;
    private String productImg;
    @NotNull
    private double productPriceNew;
    @NotNull
    private String productDescription;
    @NotNull
    private double productPriceOld;

    private int productStatus;

    private int sellNumber;
    @NotNull
    private Long categoryId;

    private Long productId;


    public ProductDto() {}

    public Long getProductId() {
        return productId;
    }

    public int getSellNumber() {
        return sellNumber;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getProductPriceOld() {
        return productPriceOld;
    }

    public void setProductPriceOld(double productPriceOld) {
        this.productPriceOld = productPriceOld;
    }




    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public double getProductPriceNew() {
        return productPriceNew;
    }

    public void setProductPriceNew(double productPriceNew) {
        this.productPriceNew = productPriceNew;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


}
