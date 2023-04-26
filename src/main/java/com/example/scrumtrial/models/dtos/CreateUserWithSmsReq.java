package com.example.scrumtrial.models.dtos;

import lombok.Data;

@Data
public class CreateUserWithSmsReq {
    private String name;
    private String sms;
}
