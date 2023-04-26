package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Services.UserService;
import com.example.scrumtrial.models.dtos.CreateUserWithEmailReq;
import com.example.scrumtrial.models.dtos.CreateUserWithSmsReq;
import com.example.scrumtrial.models.dtos.LoginReply;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
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

    @PostMapping("/usr/getCode/email")
    public ResponseEntity<?> createUserWithEmail(@RequestBody CreateUserWithEmailReq req){
        // TODO: FIX
        Verification verification;
        try {
            verification = Verification.creator(
                    ssid.getSid(),
                    req.getEmail(),
                    "email"
            ).create();
        } catch (Exception e){
            // TODO: FULL ERROR LOGGING
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().body(new LoginReply().error(Optional.of("Failed to send authentication")));
    }

    @GetMapping("/usr/checkCode")
    public ResponseEntity<?> checkUserCode(@RequestBody CreateUserWithEmailReq req){
        VerificationCheck vc;
        try {
            vc = VerificationCheck.creator(
                    ssid.getSid())
                    .setTo(req.getEmail())
                    .setCode(req.getCode())
                    .create();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        String sT = String.valueOf(Objects.hash(req.getEmail(), ZonedDateTime.now()));
        udm.createUser(User
                .withUsername(req.getEmail())
                .password(sT).build());
        uService.saveUser(req);
        return ResponseEntity.ok(new LoginReply().success(true).sessionToken(Optional.of(sT)));
    }

    @PostMapping("/usr/getCode/sms")
    public ResponseEntity<?> createUserWithSms(@RequestBody CreateUserWithSmsReq req){
        Verification verification;
        try {
            verification = Verification.creator(
                    ssid.getSid(),
                    req.getSms(),
                    "sms"
            ).createAsync().get();
        } catch (Exception e){
            // TODO: FULL ERROR LOGGING
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        if(verification.getValid()){
            String sT = String.valueOf(Objects.hash(req.getSms(), ZonedDateTime.now()));
            udm.createUser(User
                    .withUsername(req.getSms())
                    .password(sT).build());
            uService.saveUser(req);
            return ResponseEntity.ok(new LoginReply().success(true).sessionToken(Optional.of(sT)));
        }
        return ResponseEntity.badRequest().body(new LoginReply().error(Optional.of("Failed to authenticate")));
    }
}
