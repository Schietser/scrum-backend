package com.example.scrumtrial.flow.exceptions;

public class EmailVerificationException extends com.twilio.exception.ApiException{
    public EmailVerificationException(String message) {
        super(message);
    }

    public EmailVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
