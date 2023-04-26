package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Services.UserService;
import com.example.scrumtrial.models.dtos.CreateUserWithEmailReq;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/registration")
public class RegistrationController {
    private Service ssid;
    private final UserService uService;
    private final InMemoryUserDetailsManager udm;

    public RegistrationController(@Value("${TWILIO_ACCOUNT_SID}") String sid, @Value("${TWILIO_AUTH_TOKEN}") String token, UserService uService, InMemoryUserDetailsManager iudm){
        this.uService = uService;
        this.udm = iudm;
        this.ssid = Service.creator("verificationService").create();
        Twilio.init(sid, token);
    }

    @PostMapping("/registration/usr/email")
    public void createUserWithEmail(@RequestBody CreateUserWithEmailReq cu){
    }
}
