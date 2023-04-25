package com.example.scrumtrial.models.repositories;

import com.example.scrumtrial.models.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
}
