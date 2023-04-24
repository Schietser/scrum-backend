package com.example.scrumtrial.entities.models.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.Objects;
import java.util.Optional;

@Document("users")
@Getter
public class UserEntity {
    @Id
    Long id;

    @Setter
    String name;
    @Email
    String email;
    Optional<String> sms = Optional.empty();

    public UserEntity(String name, String email){
        this.id = (long) Objects.hash(name, email);
        this.name = name;
        this.email = email;
    }
}
