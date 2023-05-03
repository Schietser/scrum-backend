package com.example.scrumtrial.flow.mappers;

import com.example.scrumtrial.models.dtos.CreateUserWithEmailReq;
import com.example.scrumtrial.models.dtos.CreateUserWithPhoneReq;
import com.example.scrumtrial.models.dtos.UserResponse;
import com.example.scrumtrial.models.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserEntity toEntity(CreateUserWithEmailReq req){
        return new UserEntity(req.getEmail(), req.getCode());
    }

    public UserEntity toEntity(CreateUserWithPhoneReq req){
        return new UserEntity(req.getPhone(), req.getCode());
    }

    public UserResponse toResponse(UserEntity ent){
        return new UserResponse(ent.getId(),ent.getName(),ent.getEmail(), ent.getPhone());
    }


}
