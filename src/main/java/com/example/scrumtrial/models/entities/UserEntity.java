package com.example.scrumtrial.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.Objects;
import java.util.Optional;

@Document("users")
@Getter
@Setter
@NoArgsConstructor
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
