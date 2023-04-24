package com.example.scrumtrial.entities.repositories;

import com.example.scrumtrial.entities.models.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, Long> {
}
