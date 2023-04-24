package com.example.scrumtrial.entities.models.Entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document("Messages")
@Getter
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
