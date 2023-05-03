package com.example.scrumtrial.flow.mappers;

import com.example.scrumtrial.models.dtos.*;
import com.example.scrumtrial.models.entities.PhoneEntity;

public class PhoneMapper {
    public PhoneEntity toEntity(String identifier, String code){
        PhoneEntity p = new PhoneEntity();
        p.setIdentifier(identifier);
        p.setCode(code);
        return p;
    }

    public PhoneEntity toEntity(PhoneRequest req){
        return toEntity(req.getIdentifier(), req.getCode());
    }

    public PhoneResponse toResponse(PhoneEntity res){
        return new PhoneResponse(null, res.getIdentifier(), res.getCode());
    }
}
