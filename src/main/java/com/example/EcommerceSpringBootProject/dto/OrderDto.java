package com.example.EcommerceSpringBootProject.dto;

import com.example.EcommerceSpringBootProject.entity.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderDto extends BaseDto{

    @NotNull
    private Long userId;

    private Date createdDate;

    @NotNull
    private Long productId;

    @NotNull
    @NotEmpty
    private String customerName;

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @NotNull
    private String customerEmail;

    private Product product;

    @NotNull
    @NotEmpty
    private String customerAddress;

    @NotNull
    @NotEmpty
    private String customerPhone;

    @NotNull
    private double totalPrice;
    private String peopelQuantity;

    @NotNull
    @NotEmpty
    private String status;
    private String customerNote;

    public String getPeopelQuantity() {
        return peopelQuantity;
    }

    public void setPeopelQuantity(String peopelQuantity) {
        this.peopelQuantity = peopelQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct(){
        return this.product;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


}
