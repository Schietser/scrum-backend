package com.example.scrumtrial.entities.repositories;

import com.example.scrumtrial.entities.models.Entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
}
