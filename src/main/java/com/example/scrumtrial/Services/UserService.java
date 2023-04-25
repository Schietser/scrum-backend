package com.example.scrumtrial.Services;

import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

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

}
