package com.example.backendpsw.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User not found");
    }

}
