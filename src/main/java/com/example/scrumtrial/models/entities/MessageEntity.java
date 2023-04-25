package com.example.scrumtrial.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("Messages")
@Getter
@Setter
@NoArgsConstructor
public class MessageEntity {
    @Id
    Long id;

    UserEntity from;
    List<UserEntity> to;
    String content;

    public MessageEntity(UserEntity from, List<UserEntity> to, String c){
        this.from = from;
        this.to = to;
        this.content = c;
        this.id = (long) Objects.hash(from, to, c);
    }
}