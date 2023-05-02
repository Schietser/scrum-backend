package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Services.UserService;
import com.example.scrumtrial.models.dtos.CheckNewUserEmail;
import com.example.scrumtrial.models.dtos.CreateUserWithEmailReq;
import com.example.scrumtrial.models.dtos.CreateUserWithSmsReq;
import com.example.scrumtrial.models.dtos.LoginReply;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

// TODO: CHECK FOR UNIQUENESS OF ACC CREATION IDENTIFIER
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private Service ssid;
    private final UserService uService;
//    private final InMemoryUserDetailsManager udm;

    private enum vStatus{
        PENDING("pending"),
        APPROVED("approved"),
        CANCELLED("canceled");

        private String val;

        vStatus(String str){
            this.val = str;
        }
    }

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

    @GetMapping("/usr/checkCode")
    public ResponseEntity<LoginReply> createUser(@RequestBody CheckNewUserEmail req){
        VerificationCheck vc;
        try {
            vc = checkVerificationCode(req.getEmail(), req.getCode());
        } catch (Exception e){
            /* TODO: FULL ERROR LOGGING */
            /* and reply error-logic */
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of(e.getMessage())));
        }
        if(vc.getValid()) {
            String sT = String.valueOf(Objects.hash(req.getEmail(), ZonedDateTime.now(), vc.getChannel()));
//            udm.createUser(User
//                    .withUsername(req.getEmail())
//                    .password(sT).build());
            uService.saveUser(req);
            return ResponseEntity.ok(new LoginReply(true).sessionToken(Optional.of(sT)));
        }
        return ResponseEntity.badRequest().body(new LoginReply(false).error(Optional.of("Failed to authenticate")));
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
}
