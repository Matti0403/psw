package com.example.backendpsw.exceptions;

public class PriceChangedException extends Exception{

    public PriceChangedException(String prod){
        super("Price of product "+ prod+ " has changed");
    }
}