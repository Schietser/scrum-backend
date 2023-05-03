package com.example.scrumtrial.models.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PhoneResponse {
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
