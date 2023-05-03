package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Flow.Services.ValidationService;
import com.example.scrumtrial.Flow.exceptions.UserNotFoundException;
import com.example.scrumtrial.models.dtos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admins")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AdminController {

    private final ValidationService vs;

    public AdminController(ValidationService vs) {
        this.vs = vs;
    }


    @GetMapping("/")
    public List<ValidationResponse> findAllApi(){
        return this.vs.findAll();
    }


}
