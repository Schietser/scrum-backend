package com.example.scrumtrial.models.dtos;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserWithPhoneReq {
    private String name;
    private String phone;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserWithPhoneReq)) return false;
        CreateUserWithPhoneReq that = (CreateUserWithPhoneReq) o;
        return getPhone().equals(that.getPhone()) && getCode().equals(that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone(), getCode());
    }
}
