package com.example.scrumtrial.models.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CheckNewUserEmail {
    private String email;
    private String name;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckNewUserEmail)) return false;
        CheckNewUserEmail that = (CheckNewUserEmail) o;
        return getEmail().equals(that.getEmail()) && Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getCode());
    }
}
