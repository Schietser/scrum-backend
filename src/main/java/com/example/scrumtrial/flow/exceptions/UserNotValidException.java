package com.example.scrumtrial.flow.exceptions;

public class UserNotValidException extends RuntimeException{

    public UserNotValidException(String message) {
        super(message);
    }
}
