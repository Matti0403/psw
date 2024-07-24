package com.example.backendpsw.exceptions;

public class InvalidCredentials extends Throwable {
    public InvalidCredentials(String invalidCredentials) {
        super(invalidCredentials);
    }
}