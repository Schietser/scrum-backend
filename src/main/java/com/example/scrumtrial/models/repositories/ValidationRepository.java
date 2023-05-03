package com.example.scrumtrial.models.repositories;

import java.util.List;
import java.util.Optional;

import com.example.scrumtrial.models.entities.ValidationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ValidationRepository extends MongoRepository<ValidationEntity, String> {
    
    List<ValidationEntity> findByIdentifier(String identifier);
    List<ValidationEntity> findByPhoneValidate(String phone);

    void deleteByIdentifier(String identifier);


    Optional<ValidationEntity> findByIdentifierAndCode(String identifier, String code);
    Optional<ValidationEntity> findByPhoneAndCode(String code);

}
