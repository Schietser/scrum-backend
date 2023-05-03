package com.example.scrumtrial.flow.services;

import com.example.scrumtrial.flow.exceptions.InvalidVerificationCodeException;
import com.example.scrumtrial.models.entities.ValidationEntity;
import com.example.scrumtrial.models.repositories.ValidationRepository;
import com.twilio.Twilio;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.AuthenticationException;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class PhoneService {

    private String sid;
    private String token;
    private final ValidationRepository vRepo;
    private final String twilioNumber;

    public PhoneService(@Value("${TWILIO_ACCOUNT_SID}") String sid,
                        @Value("${TWILIO_AUTH_TOKEN}") String token,
                        final ValidationRepository vRepo,
                        @Value("${TWILIO_NUMBER}") String twilioNumber) {
        this.sid = sid;
        this.token = token;
        this.vRepo = vRepo;
        this.twilioNumber = twilioNumber;
    }

    public void sendSmsWithValidationCode(final String phone) throws AuthenticationException, TwilioException {

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phoneToValidate cannot be null or blank");
        }

        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        Twilio.init(sid, token);

        Message validationMessage = Message.creator(new PhoneNumber(phone), new PhoneNumber(twilioNumber), "Validation code: " + code).create();
        log.info("SMS sent to {} with validation code {}. Message SID: {}", phone, code, validationMessage.getSid());

        if (validationMessage.getStatus() != Message.Status.SENT) {
            throw new ApiConnectionException("Error sending SMS message: " + validationMessage.getStatus().toString());
        }

        // save the validation code to the database
        ValidationEntity v = vRepo.save(new ValidationEntity(phone, code));
        log.info("Validation code {} saved to database with id {}", v.getCode(), v.getId());
    }

    // check if the verification code is valid
    public void registerWithPhoneCode(String email, String name, String phoneToValidate, String code) throws InvalidVerificationCodeException {
        Optional<ValidationEntity> validation = vRepo.findByPhoneAndCode(code);
        if (validation == null) {
            throw new InvalidVerificationCodeException("Invalid verification code");
        }
    }


}
