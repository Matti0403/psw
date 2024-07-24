package com.example.backendpsw.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String emailOrUsernameAlreadyExists){
        super("User already exists");
    }
}
