package com.example.scrumtrial.Flow.Services;

import com.example.scrumtrial.models.dtos.CheckNewUserEmail;
import com.example.scrumtrial.models.dtos.CreateUserWithEmailReq;
import com.example.scrumtrial.models.dtos.CreateUserWithSmsReq;
import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UserService {
    private final UserRepository uRep;

    public UserService(UserRepository uRep){
        this.uRep = uRep;
    }

    public UserEntity findUserByEmail(String email) throws Exception{
        return uRep.findUserEntityByEmail(email).orElseThrow();
    }

    public UserEntity findUserBySms(String sms) throws Exception{
        return uRep.findUserEntityBySms(sms).orElseThrow();
    }

    public UserEntity saveUser(CreateUserWithEmailReq req){
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setEmail(req.getEmail());
        e.setLastLogin(ZonedDateTime.now());
        return uRep.save(e);
    }

    public UserEntity saveUser(CreateUserWithSmsReq req){
        UserEntity e = new UserEntity();
        e.setName(req.getName());
        e.setSms(req.getSms());
        e.setLastLogin(ZonedDateTime.now());
        return uRep.save(e);
    }

    public UserEntity saveUser(CheckNewUserEmail req){
        return null;
    }
}
