package com.example.scrumtrial.models.dtos;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class CreateUserWithEmailReq {
    @Email
    private String email;
    private String name;
    private String code;
}
