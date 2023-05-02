package com.example.scrumtrial.models.repositories;

import com.example.scrumtrial.models.entities.MessageEntity;
import com.example.scrumtrial.models.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByFrom(UserEntity from);
    List<MessageEntity> findAllByToContains(UserEntity to);
}
