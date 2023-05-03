package com.example.scrumtrial.controllers;

import com.example.scrumtrial.flow.services.EmailService;
import com.example.scrumtrial.flow.services.PhoneService;
import com.example.scrumtrial.flow.services.UserService;
import com.example.scrumtrial.flow.services.ValidationService;
import com.example.scrumtrial.models.dtos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    private final EmailService es;
    private final UserService us;
    private final PhoneService ps;

    private final ValidationService vs;

    public UserController(EmailService es, UserService us, PhoneService ps, ValidationService vs) {
        this.es = es;
        this.us = us;
        this.ps = ps;
        this.vs = vs;
    }

    @PutMapping("/validate/email")
    public void validateApiWithEmail(@RequestParam String email){

        log.info("Validation started");
        this.es.sendValidationCodeEmail(email);
        log.info("Validation ended");
    }

    @PutMapping("/validate/phone")
    public void validateApiWithPhone(@RequestParam String phoneToValidate){

        log.info("Validation started");
        this.ps.sendSmsWithValidationCode(phoneToValidate);
        log.info("Validation ended");
    }

    @PostMapping("/register/email")
    public void registerApiWithEmail(@RequestBody CreateUserWithEmailReq req){


        Optional<UserResponse> foundUser = this.us.findUserByEmail(req.getEmail());
        if (foundUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        ValidationRequest vq = new ValidationRequest(req.getEmail(), req.getCode());

        Optional<ValidationResponse> response = this.vs.find(vq);

       if (response.isPresent()){
           this.us.saveUser(req);
       }

    }

    @PostMapping("/register/phone")
    public void registerApiWithPhone(@RequestBody CreateUserWithPhoneReq req){

        Optional<UserResponse> foundUser = this.us.findUserByPhone(req.getPhone());
        if (foundUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        ValidationRequest vq = new ValidationRequest(req.getPhone(), req.getCode());

        Optional<ValidationResponse> response = this.vs.find(vq);

        if (response.isPresent()){
            this.us.saveUser(req);
        }

    }

    @PutMapping("/login")
    public void loginApi(@RequestBody EmailLoginRequest req){

    }





}
