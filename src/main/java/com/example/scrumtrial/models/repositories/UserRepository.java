package com.example.scrumtrial.models.repositories;

import com.example.scrumtrial.models.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);
    Optional<UserEntity> findUserEntityByPhone(String phone);
}
