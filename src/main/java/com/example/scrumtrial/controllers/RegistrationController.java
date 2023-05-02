package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Flow.Services.UserService;
import com.example.scrumtrial.models.dtos.*;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//import java.time.ZonedDateTime;
//import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

// TODO: CHECK FOR UNIQUENESS OF ACC CREATION IDENTIFIER
@RestController
@RequestMapping("/registration")
@Slf4j
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

    private VerificationCheck checkVerificationCode(String identifier, String code) {
        return VerificationCheck.creator(
                ssid.getSid())
                .setTo(identifier)
                .setCode(code)
                .create();
    }

    private LoginReply validateAndSave(Object req, Supplier<String> identifier_supp, Supplier<String> code_supp){
        try {
            vc = checkVerificationCode(identifier_supp.get(), code_supp.get());
        } catch (Exception e){
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        if(vc.getValid()) {
            if(req instanceof CheckNewUserEmail){
                uService.saveUser((CheckNewUserEmail) req);
            } else if (req instanceof CheckNewUserSms) {
                uService.saveUser((CheckNewUserSms) req);
            } else{
                log.error("there is an unimplemented type ඞ among us ඞ");
                log.error("YOU FORGOR \uD83D\uDC80 TO IMPL TYPE " + req.getClass().getSimpleName());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "I forgor \uD83D\uDC80");
            }
            return new LoginReply(true);
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"Failed to authenticate");
    }

    @PostMapping("/usr/getCode/email")
    public LoginReply sendVerificationCode(@RequestBody CreateUserWithEmailReq req){
        try {
            sendVerificationCode(req.getEmail(), "email");
            return new LoginReply(true);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/usr/checkCode/email")
    public LoginReply createUserIfCodeIsValid(@RequestBody CheckNewUserEmail req){
        return validateAndSave(req, req::getEmail, req::getCode);
    }

    @PostMapping("/usr/getCode/sms")
    public LoginReply sendVerificationCode(@RequestBody CreateUserWithSmsReq req){
        try {
            sendVerificationCode(req.getSms(), "sms");
            return new LoginReply(true);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/usr/checkCode/sms")
    public LoginReply createUserIfCodeIsValid(@RequestBody CheckNewUserSms req){
        return validateAndSave(req, req::getSms, req::getCode);
    }
}
