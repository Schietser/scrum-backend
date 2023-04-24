package com.example.scrumtrial;

import com.example.scrumtrial.entities.models.MessageEntity;
import com.example.scrumtrial.entities.models.UserEntity;
import com.example.scrumtrial.entities.repositories.MessageRepository;
import com.example.scrumtrial.entities.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RestController
public class Controller {
    private final UserRepository ur;
    private final MessageRepository mr;
    private  final Random r = new Random();

    private final Faker f;

    public Controller(UserRepository ur, MessageRepository mr){
        this.mr = mr;
        this.ur = ur;
        this.f = new Faker();
    }

    @GetMapping("/")
    public String user(){
        UserEntity tue = new UserEntity(f.name().username(), f.name().nameWithMiddle());
        ur.save(tue);
        return "sucess";
    }

    @GetMapping("/m")
    public String msg(){
        List<UserEntity> usrs = ur.findAll();
        List<UserEntity> toUsers = new LinkedList<>();
        UserEntity fu = usrs.get(r.nextInt(usrs.size()));
        for (int i = r.nextInt(usrs.size()/2); i < r.nextInt(usrs.size()/2, usrs.size()); i++) {
            toUsers.add(usrs.get(i));
        }
        mr.save(new MessageEntity(fu, toUsers, f.lorem().sentence()));
        return "sucess";
    }
}
