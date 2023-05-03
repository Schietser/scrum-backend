package com.example.scrumtrial.Flow.Services;

import com.example.scrumtrial.models.repositories.ValidationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    private String sid;
    private String token;
    private final ValidationRepository vRepo;

    public PhoneService(@Value("${TWILIO_ACCOUNT_SID}") String sid, @Value("${TWILIO_AUTH_TOKEN}") String token, ValidationRepository vRepo) {
        this.sid = sid;
        this.token = token;
        this.vRepo = vRepo;
    }

    public void sendSms(final String from, final String to, String subject, String body) {

    }

    public void sendValidationCode(final String phoneToValidate) throws IllegalArgumentException {

    }

}
