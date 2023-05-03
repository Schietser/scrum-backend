package com.example.scrumtrial.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.ZonedDateTime;
import java.util.Objects;

@Document("users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    String id;

    @Setter
    String name;
    @Email
    @Indexed(unique = true)
    String email;
    @Indexed(unique = true)
    String sms;
    ZonedDateTime lastLogin;

    public UserEntity(String name, String email){
        this.name = name;
        this.email = email;
        this.lastLogin = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getSms(), that.getSms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getSms());
    }
}
