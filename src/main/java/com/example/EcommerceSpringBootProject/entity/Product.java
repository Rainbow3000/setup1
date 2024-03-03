package com.example.EcommerceSpringBootProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String productDescription ;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    @Length(min = 5, max = 256)
    @NotNull
    private String productName;
    private String productImg;
    @NotNull
    private double productPriceNew;
    @NotNull
    private double productPriceOld;

    @NotNull
    private int sellNumber;
    @NotNull
    private int productStatus;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    public int getSellNumber(){
        return sellNumber;
    }
    public String getProductName() {
        return productName;
    }

    public int setSellNumber(int qty) {
        return qty;
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

    public double getProductPriceNew() {
        return productPriceNew;
    }

    public void setProductPriceNew(double productPriceNew) {
        this.productPriceNew = productPriceNew;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
