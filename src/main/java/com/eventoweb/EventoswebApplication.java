package com.eventoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EventoswebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventoswebApplication.class, args);
          System.out.print(new BCryptPasswordEncoder().encode("123"));
        System.out.print(new BCryptPasswordEncoder().encode("123"));
    }
}