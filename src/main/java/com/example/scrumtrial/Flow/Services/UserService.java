package com.example.scrumtrial.Flow.Services;

import com.example.scrumtrial.Flow.Mappers.UserMapper;
import com.example.scrumtrial.Flow.exceptions.UserNotFoundException;
import com.example.scrumtrial.models.dtos.*;
import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository uRep;
    private final UserMapper um;

    public UserService(UserRepository uRep, UserMapper um){
        this.uRep = uRep;
        this.um = um;
    }

    public Optional<UserResponse> findUserByEmail(String email) throws UserNotFoundException {
        Optional<UserEntity> foundUser =  uRep.findUserEntityByEmail(email);
        return foundUser.map(um::toResponse);
    }

    public Optional<UserResponse> findUserByPhone(String phone) throws UserNotFoundException {
        Optional<UserEntity> foundUser =  uRep.findUserEntityByPhone(phone);
        return foundUser.map(um::toResponse);
    }

    public void saveUser(CreateUserWithEmailReq req){
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        uRep.save(e);
    }

    public void saveUser(CreateUserWithPhoneReq req){
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setPhone(req.getPhone());
        uRep.save(e);
    }

    public void saveUser(CheckNewUserEmail req){
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        uRep.save(e);
    }

    public void saveUser(CheckNewUserSms req) {
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setPhone(req.getPhone());
        uRep.save(e);
    }
}
