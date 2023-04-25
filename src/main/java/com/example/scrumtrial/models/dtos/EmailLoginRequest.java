package com.example.scrumtrial.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmailLoginRequest {
    @Email
    String email;
}
