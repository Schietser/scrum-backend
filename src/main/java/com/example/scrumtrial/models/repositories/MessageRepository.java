package com.example.scrumtrial.models.repositories;

import com.example.scrumtrial.models.entities.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, Long> {
}
