package com.example.scrumtrial.models.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ValidationRequest {
    
    @NotBlank
    String identifier;


    String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationRequest)) return false;
        ValidationRequest that = (ValidationRequest) o;
        return getIdentifier().equals(that.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }
}
