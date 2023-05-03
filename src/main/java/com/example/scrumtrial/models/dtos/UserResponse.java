package com.example.scrumtrial.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String id;

    String name;
    @Email
    @Indexed(unique = true)
    String email;
    @Indexed(unique = true)
    String phone;

    public UserResponse(String name, String email){
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPhone());
    }
}
