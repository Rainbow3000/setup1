package com.example.EcommerceSpringBootProject.exception;
public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
