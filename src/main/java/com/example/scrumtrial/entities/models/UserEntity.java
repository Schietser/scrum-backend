package com.example.scrumtrial.entities.models;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("users")
@Getter
public class UserEntity {
    @Id
    Long id;

    @Setter
    String name;

    @Email
    String email;


    String sms;

    public UserEntity(String name, String email){
        this.id = (long) Objects.hash(name, email);
        this.name = name;
        this.email = email;
    }
}
