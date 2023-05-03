package com.example.scrumtrial.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("validations")
@Getter
@Setter
@NoArgsConstructor
public class ValidationEntity {
    
    @Id
    private String id;
    private String identifier;
    private String code;

    public ValidationEntity(String identifier, String code){
        this.identifier = identifier;
        this.code = code;
    }
}
