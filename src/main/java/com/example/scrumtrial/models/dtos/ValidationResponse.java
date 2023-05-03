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
public class ValidationResponse {

    String id;

    @NotBlank
    String identifier;

    @NotBlank
    @Length(min = 6, max = 6)
    String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationResponse)) return false;
        ValidationResponse that = (ValidationResponse) o;
        return Objects.equals(getId(), that.getId()) && getIdentifier().equals(that.getIdentifier()) && getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdentifier(), getCode());
    }
}
