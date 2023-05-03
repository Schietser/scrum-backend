package com.example.scrumtrial.flow.mappers;

import org.springframework.stereotype.Component;

import com.example.scrumtrial.models.dtos.ValidationRequest;
import com.example.scrumtrial.models.dtos.ValidationResponse;
import com.example.scrumtrial.models.entities.ValidationEntity;

@Component
public class ValidationMapper {
    

    public ValidationEntity toEntity(String identifier, String code){
        ValidationEntity v = new ValidationEntity();
        v.setIdentifier(identifier);
        v.setCode(code);
        return v;
    }

    public ValidationEntity toEntity(ValidationRequest req){
        return toEntity(req.getIdentifier(), req.getCode());
    }

    public ValidationResponse toResponse(ValidationEntity res){
        return new ValidationResponse(null, res.getIdentifier(), res.getCode());
    }


}
