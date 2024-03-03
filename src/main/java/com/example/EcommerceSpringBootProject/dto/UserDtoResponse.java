package com.example.EcommerceSpringBootProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.*;

public class UserDtoResponse extends BaseDto {
    private Date createdDate;
    @Length(min = 10,max = 100)
    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Length(min = 8)
    @NotNull
    private String password;
    @NotNull
    private int status;





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return this.email;
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
