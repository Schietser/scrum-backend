package com.example.scrumtrial.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailReq {

    @Email
    private String from;
    @Email
    private String to;
    @NotBlank
    private String subject;
    private String body;
}
