package com.example.scrumtrial.controllers;

import com.example.scrumtrial.flow.services.ValidationService;
import com.example.scrumtrial.models.dtos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
