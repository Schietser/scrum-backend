package com.example.scrumtrial.models.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ValidationResponse {

    String id;

    @NotBlank
    String identifier;

    @NotBlank
    @Length(min = 6, max = 6)
    String code;
}
