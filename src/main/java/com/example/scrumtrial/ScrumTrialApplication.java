package com.example.scrumtrial;

import com.example.scrumtrial.models.entities.MessageEntity;
import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.MessageRepository;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.List;

@SpringBootApplication
@EnableRedisHttpSession
@EnableRedisRepositories
public class ScrumTrialApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    public ScrumTrialApplication(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(ScrumTrialApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // Remove method content if want NO TEST data

        UserEntity user=new UserEntity();
        user.setName("User1");
        user.setEmail("user1@mail.com");
        user.setPhone("320457758045");

        userRepository.save(user);

        UserEntity user2=new UserEntity();
        user2.setName("User2");
        user2.setEmail("user2@mail.com");
        user2.setPhone("320457758599");

        userRepository.save(user2);

        UserEntity user3=new UserEntity();
        user3.setName("User3");
        user3.setEmail("user3@mail.com");
        user3.setPhone("320457758899");

        userRepository.save(user3);


        MessageEntity message=new MessageEntity();
        List<UserEntity> receivers=List.of(user2);
        message.setTo(receivers);
        message.setFrom(user);
        message.setContent("Test Message sent");

        messageRepository.save(message);

    }
}
