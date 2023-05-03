package com.example.scrumtrial.models.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.scrumtrial.models.entities.ValidationEntity;

public interface ValidationRepository extends MongoRepository<ValidationEntity, String> {
    
    List<ValidationEntity> findByIdentifier(String identifier);

    void deleteByIdentifier(String identifier);

    

}
