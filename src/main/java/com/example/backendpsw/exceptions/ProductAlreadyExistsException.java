package com.example.backendpsw.exceptions;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(){
        super("Product already exists");
    }
}