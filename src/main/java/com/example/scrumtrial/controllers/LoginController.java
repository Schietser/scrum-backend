package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Services.UserService;
import com.example.scrumtrial.models.dtos.EmailLoginRequest;
import com.example.scrumtrial.models.dtos.PhoneLoginRequest;
import com.example.scrumtrial.models.entities.UserEntity;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController("login/")
public class LoginController {
    private String sid;
    private String token;
    private Service ssid;
    private final UserService uService;
    private final String twilioVerifivicationLink;

    private final ZonedDateTime dt = ZonedDateTime.now();

    private LoginController(@Value("${TWILIO_ACCOUNT_SID}") String sid, @Value("${TWILIO_AUTH_TOKEN}") String token, UserService uService){
        this.sid = sid;
        this.token = token;
        this.ssid = Service.creator("verificationService").create();
        this.uService = uService;
        this.twilioVerifivicationLink = "https://verify.twilio.com/v2/Services/" + this.ssid + "/VerificationCheck";
        Twilio.init(sid, token);
    }

    public Optional<ResponseEntity<String>> checkUserLoginTime(UserEntity ue){
        if(ChronoUnit.MINUTES.between(ue.getLastLogin(), ZonedDateTime.now()) < 1){
            return Optional.of(ResponseEntity.badRequest().body("User tried to login too recently, wait a minute between logins"));
        }
        ue.setLastLogin(ZonedDateTime.now());
        return Optional.empty();
    }

    @PostMapping("login/usr/email")
    public ResponseEntity<String> loginAttempt(@RequestBody EmailLoginRequest req){
        try {
            Optional<ResponseEntity<String>> or = checkUserLoginTime(uService.findUserByEmail(req.getEmail()));
            if(or.isPresent()){
                return or.get();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User with email:" +  req.getEmail() + " does not exist");
        }
        Verification verification;
        try {
            verification = Verification.creator(
                    ssid.getSid(),
                    req.getEmail(),
                    "email"
            ).createAsync().get();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok("");
    }

    @PostMapping("login/usr/phone")
    public ResponseEntity<String> loginAttempt(@RequestBody PhoneLoginRequest req){
        Verification verification;
        try {
            verification = Verification.creator(
                    ssid.getSid(),
                    req.getNumber(),
                    "sms"
            ).create();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("");
    }
}
