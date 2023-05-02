package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Flow.Services.UserService;
import com.example.scrumtrial.models.dtos.*;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

//import java.time.ZonedDateTime;
//import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

// TODO: CHECK FOR UNIQUENESS OF ACC CREATION IDENTIFIER
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final Service ssid;
    private final UserService uService;
    private VerificationCheck vc;

//    private final InMemoryUserDetailsManager udm;

    public RegistrationController(@Value("${TWILIO_ACCOUNT_SID}") String sid, @Value("${TWILIO_AUTH_TOKEN}") String token, UserService uService/*, InMemoryUserDetailsManager iudm*/){
        this.uService = uService;
//        this.udm = iudm;
        this.ssid = Service.creator("verificationService").create();
        Twilio.init(sid, token);
    }

    private void sendVerificationCode(String adress, String channel) throws Exception{
        Verification
                .creator(ssid.getSid(), adress, channel)
                .createAsync().get();
    }

    private VerificationCheck checkVerificationCode(String identifier, String code) throws Exception{
        return VerificationCheck.creator(
                ssid.getSid())
                .setTo(identifier)
                .setCode(code)
                .create();
    }

    public ResponseEntity<LoginReply> an_even_more_suspicious_fun(Object req, Supplier<String> identifier_supp, Supplier<String> code_supp){
        try {
            vc = checkVerificationCode(identifier_supp.get(), code_supp.get());
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of(e.getMessage())));
        }
        if(vc.getValid()) {
            if(req instanceof CheckNewUserEmail){
                uService.saveUser((CheckNewUserEmail) req);
            } else if (req instanceof CheckNewUserSms) {
                uService.saveUser((CheckNewUserSms) req);
            } else{
                System.err.println("there is an unimplemented type ඞ among us ඞ");
                System.err.println("YOU FORGOR \uD83D\uDC80 TO IMPL TYPE " + req.getClass().getSimpleName());
                return ResponseEntity.internalServerError().body(new LoginReply(false).error(Optional.of("I forgor \uD83D\uDC80")));
            }
            return ResponseEntity.ok(new LoginReply(true));
        }
        return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of("Failed to authenticate")));
    }

    @PostMapping("/usr/getCode/email")
    public ResponseEntity<LoginReply> createUserWithEmail(@RequestBody CreateUserWithEmailReq req){
        try {
            sendVerificationCode(req.getEmail(), "email");
            return ResponseEntity.ok().body(new LoginReply(true));
        } catch (Exception e){
            /* TODO: FULL ERROR LOGGING */
            /* and reply error-logic */
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of(e.getMessage())));
        }
    }

    @PostMapping("/usr/checkCode/email")
    public ResponseEntity<LoginReply> createUser(@RequestBody CheckNewUserEmail req){
        return an_even_more_suspicious_fun(req, req::getEmail, req::getCode);
    }

    @PostMapping("/usr/getCode/sms")
    public ResponseEntity<LoginReply> createUserWithSms(@RequestBody CreateUserWithSmsReq req){
        try {
            sendVerificationCode(req.getSms(), "sms");
            return ResponseEntity.ok(new LoginReply(true));
        } catch (Exception e){
            // TODO: FULL ERROR LOGGING
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of(e.getMessage())));
        }
    }

    @PostMapping("/usr/checkCode/sms")
    public ResponseEntity<LoginReply> createUser(@RequestBody CheckNewUserSms req){
        return an_even_more_suspicious_fun(req, req::getSms, req::getCode);
    }
}
