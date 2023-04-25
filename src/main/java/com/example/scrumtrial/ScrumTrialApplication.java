package com.example.scrumtrial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableRedisRepositories
public class ScrumTrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumTrialApplication.class, args);
    }

}
