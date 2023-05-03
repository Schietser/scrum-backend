package com.example.scrumtrial.Flow.exceptions;

public class EmailVerificationException extends com.twilio.exception.ApiException{
    public EmailVerificationException(String message) {
        super(message);
    }

    public EmailVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
