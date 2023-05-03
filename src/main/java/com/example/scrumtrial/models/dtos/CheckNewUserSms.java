package com.example.scrumtrial.models.dtos;

import lombok.Data;

@Data
public class CheckNewUserSms {
    private String name;
    private String phone;
    private String code;
}
