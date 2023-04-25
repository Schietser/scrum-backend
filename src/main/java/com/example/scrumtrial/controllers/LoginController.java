package com.example.scrumtrial.controllers;

import com.example.scrumtrial.models.dtos.EmailLoginRequest;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("login/")
public class LoginController {
    private String sid;
    private String token;
    private Service ssid;
    private LoginController(@Value("${TWILIO_ACCOUNT_SID}") String sid, @Value("${TWILIO_AUTH_TOKEN}") String token){
        this.sid = sid;
        this.token = token;
        this.ssid = Service.creator("verificationService").create();
        Twilio.init(sid, token);
    }

    @PostMapping("login/usr")
    public ResponseEntity<Verification> loginAttempt(@RequestBody EmailLoginRequest req){
        try {
            return ResponseEntity.ok().body(Verification.creator(
                    ssid.getSid(),
                    "+32467711709",
                    "sms"
            ).create());
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
